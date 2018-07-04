package test.provider.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import test.provider.model.User;
import test.provider.repository.MessageResponsitory;
import test.provider.repository.UserRespository;
import java.io.IOException;
import java.util.Date;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 11:47
 * @Description 类描述:
 */


@Component
public class UserListener{

    @Autowired
    UserRespository userRespository;

    @Autowired
    MessageResponsitory messageResponsitory;

    @RabbitListener(queues = "user-queue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("=================");
        User record = JSON.parseObject(new String(message.getBody()), User.class);
        long tag = message.getMessageProperties().getDeliveryTag();
        System.out.println(record);
        try {
            User user = userRespository.findOne(record.getId());
            user.setName(record.getName());
            user.setMoney(user.getMoney().add(record.getMoney().negate()));
            user.setUpdateTime(new Date());
            userRespository.save(user);
            channel.basicAck(tag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
