package test.provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import test.provider.model.Test;
import test.provider.mq.TestInPut;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 13:34
 * @Description 类描述:
 */

@Component
public class SendMsgService {

    private MessageChannel messageChannel;

    @Autowired
    public SendMsgService(@Qualifier(TestInPut.INPUT) MessageChannel output) {
        this.messageChannel = output;
    }



    public boolean sendMsg(Test test){
        GenericMessage msg = new GenericMessage(test);
        return messageChannel.send(msg);
    }
}
