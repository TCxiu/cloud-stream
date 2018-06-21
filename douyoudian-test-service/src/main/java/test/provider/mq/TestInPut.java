package test.provider.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 11:45
 * @Description 接口描述:
 */


public interface TestInPut {
    String INPUT = "test-input";

    @Input(TestInPut.INPUT)
    MessageChannel input();
}
