package test.provider.Service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import test.provider.model.Message;
import test.provider.model.User;
import test.provider.mq.TestInPut;
import test.provider.repository.MessageResponsitory;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 13:34
 * @Description 类描述:
 */

@Component
public class SendMsgService {

    @Autowired
    MessageResponsitory messageResponsitory;

    private DirectChannel messageChannel;

    @Autowired
    public SendMsgService(@Qualifier(TestInPut.INPUT) MessageChannel output) {
        this.messageChannel = (DirectChannel) output;
    }



    public boolean sendMsg(Long id,User user){
        user.setId(id);
        Message message = new Message();
        message.setStatus("预发送");
        message.setMessage(JSON.toJSONString(user));
        messageResponsitory.save(message);

        GenericMessage msg = new GenericMessage(message);
        boolean send = messageChannel.send(msg);
        return send;
    }
}
