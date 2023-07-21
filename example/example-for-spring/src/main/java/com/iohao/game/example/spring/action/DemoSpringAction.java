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
package com.iohao.game.example.spring.action;

import com.iohao.game.example.common.msg.HelloSpringMsg;
import com.iohao.game.example.spring.service.DemoSpringService;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spring demo : 这是一个 spring 管理的 action 层
 *
 * @author 渔民小镇
 * @date 2022-03-22
 */
@Component
@ActionController(DemoCmdForSpring.cmd)
public class DemoSpringAction {
    /** spring 管理的类： service 业务类 */
    @Autowired
    DemoSpringService demoSpringService;

    /**
     * spring 示例方法
     *
     * @param helloReq helloReq
     * @return HelloReq
     */
    @ActionMethod(DemoCmdForSpring.here)
    public HelloSpringMsg here(HelloSpringMsg helloReq) {
        return demoSpringService.here(helloReq);
    }
}
