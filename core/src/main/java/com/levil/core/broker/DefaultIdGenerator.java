/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.levil.core.broker;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DefaultIdGenerator implements IdGenerator {
    
    public static final String ID_DELIMITER = "#";

    
    private final String ip;
    
    private final int port;
    
    public DefaultIdGenerator(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public DefaultIdGenerator(BrokerServerMember bs) {
        this.ip = bs.getIp();
        this.port = bs.getPort();
    }
    
    @Override
    public String generateId() {
        return ip + ID_DELIMITER + port;
    }
}
