package com.levil.remoting.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class RequestMessage extends Message {
    private Integer type;

    @Override
    public int getMessageType() {
        return requestMessage;
    }
}
