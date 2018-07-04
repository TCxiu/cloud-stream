package test.provider.mq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.provider.enumeration.ExchangeTypeEnum;
import java.util.Map;

@Component
public class RabbitMqResponsitory {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    private Queue queue;

    private Exchange exchange;

    private Binding binding;

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete,Map<String, Object> arguments) {
        Queue queue = new Queue(queueName+"-queue", durable, exclusive, autoDelete, arguments);
        rabbitAdmin.declareQueue(queue);
        this.queue = queue;
    }

    public void setQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete) {
        Queue queue = new Queue(queueName+"-queue", durable, exclusive, autoDelete, null);
        rabbitAdmin.declareQueue(queue);
        this.queue = queue;
    }
    public void setQueue(String queueName, Boolean durable, Boolean exclusive) {
        Queue queue = new Queue(queueName+"-queue", durable, exclusive, true, null);
        rabbitAdmin.declareQueue(queue);
        this.queue = queue;
    }

    public void setQueue(String queueName, Boolean durable) {
        Queue queue = new Queue(queueName+"-queue", durable, false, true, null);
        rabbitAdmin.declareQueue(queue);
        this.queue = queue;
    }
    public void setQueue(String queueName) {
        Queue queue = new Queue(queueName+"-queue", true, false, false, null);
        rabbitAdmin.declareQueue(queue);
        this.queue = queue;
    }

    public Exchange getExchange() {
        return exchange;
    }


    public void setExchange(String queueName, Boolean durable, Boolean autoDelete ,ExchangeTypeEnum exchangeType) {

        if (exchangeType.getExchangeName().equals("directExchange")){
            DirectExchange directExchange = new DirectExchange(queueName+"-exchange", durable, autoDelete);
            rabbitAdmin.declareExchange(directExchange);
            this.exchange = directExchange;
        }

        if (exchangeType.getExchangeName().equals("fanoutExchange")){
            FanoutExchange fanoutExchange = new FanoutExchange(queueName+"-exchange", durable, autoDelete);
            rabbitAdmin.declareExchange(fanoutExchange);
            this.exchange = fanoutExchange;
        }

        if (exchangeType.getExchangeName().equals("topicExchange")){
            TopicExchange topicExchange = new TopicExchange(queueName+"-exchange", durable, autoDelete);
            rabbitAdmin.declareExchange(topicExchange);
            this.exchange = topicExchange;
        }

        if (exchangeType.getExchangeName().equals("headersExchange")){
            HeadersExchange headersExchange = new HeadersExchange(queueName+"-exchange", durable, autoDelete);
            rabbitAdmin.declareExchange(headersExchange);
            this.exchange = headersExchange;
        }
    }

    public void setExchange(String queueName,ExchangeTypeEnum exchangeType) {
        if (exchangeType.getExchangeName().equals("directExchange")){
            DirectExchange directExchange = new DirectExchange(queueName+"-exchange", true, false);
            rabbitAdmin.declareExchange(directExchange);
            this.exchange = directExchange;
        }

        if (exchangeType.getExchangeName().equals("fanoutExchange")){
            FanoutExchange fanoutExchange = new FanoutExchange(queueName+"-exchange", true, false);
            rabbitAdmin.declareExchange(fanoutExchange);
            this.exchange = fanoutExchange;
        }

        if (exchangeType.getExchangeName().equals("topicExchange")){
            TopicExchange topicExchange = new TopicExchange(queueName+"-exchange", true, false);
            rabbitAdmin.declareExchange(topicExchange);
            this.exchange = topicExchange;
        }

        if (exchangeType.getExchangeName().equals("headersExchange")){
            HeadersExchange headersExchange = new HeadersExchange(queueName+"-exchange", true, false);
            rabbitAdmin.declareExchange(headersExchange);
            this.exchange = headersExchange;
        }
    }


    public Binding getBinding() {
        return binding;
    }

    public void setBinding(String queueName, Map<String, Object> arguments) {
        Binding binding = new Binding(queueName+"-queue", Binding.DestinationType.QUEUE, queueName+"-exchange", queueName+"-key", arguments);
        rabbitAdmin.declareBinding(binding);
        this.binding = this.binding;
    }

    public void setBinding(String queueName) {
        Binding binding = new Binding(queueName+"-queue", Binding.DestinationType.QUEUE, queueName+"-exchange", queueName+"-key", null);
        rabbitAdmin.declareBinding(binding);
        this.binding = this.binding;
    }
}
