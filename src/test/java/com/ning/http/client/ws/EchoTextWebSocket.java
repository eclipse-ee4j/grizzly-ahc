/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ning.http.client.ws;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

public final class EchoTextWebSocket extends WebSocketAdapter {

    @Override
    public void onWebSocketText(String s) {
        if (isNotConnected()) {
            return;
        }
        try {
            if (s.equals("CLOSE"))
                super.getSession().close();
            else
                getRemote().sendString(s);
        } catch (IOException e) {
            try {
                getRemote().sendString("FAIL");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

