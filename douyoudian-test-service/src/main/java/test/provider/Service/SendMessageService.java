package test.provider.Service;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.provider.enumeration.ExchangeTypeEnum;
import test.provider.model.Message;
import test.provider.model.User;
import test.provider.mq.RabbitMqResponsitory;
import test.provider.repository.MessageResponsitory;
import test.provider.utils.IdFactory;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 13:34
 * @Description 类描述:
 */

@Component
public class SendMessageService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMqResponsitory rabbitMqResponsitory;

    @Autowired
    MessageResponsitory messageResponsitory;

    public Boolean sendMessage(Long id,ExchangeTypeEnum queueType,User user){
        String queueName = user.getClass().getSimpleName().toLowerCase();
        user.setId(id);
        rabbitMqResponsitory.setQueue(queueName);
        rabbitMqResponsitory.setExchange(queueName,queueType);
        rabbitMqResponsitory.setBinding(queueName);

        rabbitTemplate.convertAndSend(queueName + "-exchange", queueName + "-key", user);

        Message db = new Message();
        db.setId(IdFactory.nextId());
        db.setMessage(JSON.toJSONString(user));
        db.setStatus("已发送");

        Message save = messageResponsitory.save(db);
        return save ==null ? false : true;
    }
}
