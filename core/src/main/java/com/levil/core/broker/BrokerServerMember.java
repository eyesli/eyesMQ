package com.levil.core.broker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerServerMember {
    private String BrokerServerId;
    private String ip;
    private Integer port;
    private NodeState nodeState ;
}
