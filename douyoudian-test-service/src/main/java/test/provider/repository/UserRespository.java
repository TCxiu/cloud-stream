package test.provider.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import test.provider.model.User;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/07/02 13:36
 * @Description 类描述:
 */

@Component
public interface UserRespository extends CrudRepository<User,Long> {
}
