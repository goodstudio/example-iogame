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
package com.iohao.game.example.broadcast;

import com.iohao.game.example.common.DemoModuleCmd;

/**
 * @author 渔民小镇
 * @date 2022-05-18
 */
public interface DemoBroadcastCmd {
    /** 模块 - 主 cmd : 1 */
    int cmd = DemoModuleCmd.demoModule_7_cmd;

    /** 广播一些消息 */
    int broadcastMsg = 0;

    int helloBroadcast1 = 1;
}
