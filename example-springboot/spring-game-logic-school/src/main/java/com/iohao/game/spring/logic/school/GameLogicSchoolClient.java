/*
 * # iohao.com . 渔民小镇
 * Copyright (C) 2021 - 2023 double joker （262610965@qq.com） . All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.game.spring.logic.school;

import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.MyFlowContext;
import com.iohao.game.action.skeleton.core.flow.interal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.spring.logic.core.MyBarSkeletonConfig;
import com.iohao.game.spring.logic.school.action.SchoolAction;
import com.iohao.game.spring.logic.school.annotation.IgnoreDebugInout;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 学校游戏逻辑服
 *
 * @author 渔民小镇
 * @date 2022-07-09
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameLogicSchoolClient extends AbstractBrokerClientStartup {
    @Override
    public BarSkeleton createBarSkeleton() {
        // 业务框架构建器 配置
        BarSkeletonBuilderParamConfig config = MyBarSkeletonConfig.createBarSkeletonBuilderParamConfig()
                // 扫描 action 类所在包
                .scanActionPackage(SchoolAction.class);

        // 业务框架构建器
        BarSkeletonBuilder builder = config.createBuilder()
                // 设置一个自定义的 flow 上下文生产工厂
                .setFlowContextFactory(MyFlowContext::new);

        // 开启 jsr380 验证
        builder.getSetting().setValidator(true);

        // 添加控制台输出插件
        DebugInOut debugInOut = new DebugInOut();

        // 使用自定义注解的方式忽略 debug 打印
        debugInOut.setPrintConsumer((message, flowContext) -> {
            Method actionMethod = flowContext.getActionCommand().getActionMethod();
            // 从 action 中拿出自定义注解 IgnoreDebugInout
            var annotation = actionMethod.getAnnotation(IgnoreDebugInout.class);
            if (Objects.nonNull(annotation)) {
                // 表示不需要打印
                return;
            }

            System.out.println(message);
        });

        builder.addInOut(debugInOut);

        return builder.build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        BrokerClientBuilder builder = BrokerClient.newBuilder();
        builder.appName("spring school 学校游戏逻辑服");
        return builder;
    }
}
