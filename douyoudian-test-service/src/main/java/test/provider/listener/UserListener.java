package test.provider.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.provider.model.User;
import test.provider.mq.RabbitMqResponsitory;
import test.provider.repository.MessageResponsitory;
import test.provider.repository.UserRespository;
import java.io.IOException;
import java.util.Date;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 11:47
 * @Description 类描述:
 */

@EnableRabbit
@Component
public class UserListener{

    @Autowired
    UserRespository userRespository;

    @Autowired
    MessageResponsitory messageResponsitory;

    @Autowired
    RabbitMqResponsitory rabbitMqResponsitory;

    @RabbitListener(queues = "user-queue",containerFactory = "simpleRabbitListenerContainerFactory",admin = "rabbitAdmin")
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
