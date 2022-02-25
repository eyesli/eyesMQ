package com.levil.remoting.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage extends AbstractResponseMessage {

    private Integer type;

    public ResponseMessage(boolean success, String reason,Integer type) {
        super(success, reason);
        this.type=type;
    }

    @Override
    public int getMessageType() {
        return responseMessage;
    }
}
