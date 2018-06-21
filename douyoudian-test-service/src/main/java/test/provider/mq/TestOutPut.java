package test.provider.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 13:38
 * @Description 类描述:
 */

public interface TestOutPut {
    @Output(TestInPut.INPUT)
    MessageChannel output();
}
