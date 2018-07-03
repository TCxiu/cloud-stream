package test.provider.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import test.provider.model.Message;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/07/02 15:25
 * @Description 类描述:
 */
@Component
public interface MessageResponsitory extends CrudRepository<Message,Long> {
}
