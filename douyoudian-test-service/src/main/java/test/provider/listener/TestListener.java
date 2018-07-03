package test.provider.listener;

import com.alibaba.fastjson.JSON;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import test.provider.Service.UserService;
import test.provider.model.Message;
import test.provider.model.User;
import test.provider.mq.TestInPut;
import test.provider.repository.MessageResponsitory;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 11:47
 * @Description 类描述:
 */

@EnableBinding({TestInPut.class})
public class TestListener {
    @Autowired
    UserService userService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    MessageResponsitory messageResponsitory;


    @StreamListener(TestInPut.INPUT)
    public void record(Message msg) {
        RLock lock = redissonClient.getLock("lock");
        lock.lock(10, TimeUnit.MINUTES);
        User record = JSON.parseObject(msg.getMessage(), User.class);
        User user = userService.finduserById(record.getId());

        user.setName(record.getName());
        user.setMoney(user.getMoney().add(record.getMoney().negate()));

        user.setUpdateTime(new Date());

        userService.updateUser(user.getId(),user);

        msg.setStatus("已接收");
        msg.setMessage(JSON.toJSONString(user));
        messageResponsitory.save(msg);

        lock.unlock();
//            Test test = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(record.getId())), Test.class, "tests");
//            test.setMoney(test.getMoney().add(record.getMoney().negate()));
//            Update update = new Update();
//            update.set("_id",test.getId())
//                    .set("_class",test.getClass().getName())
//                    .set("birthday",new Date())
//                    .set("name",record.getName())
//                    .set("money",test.getMoney());
//
//            WriteResult writeResult = mongoTemplate.updateMulti(
//            Query.query(Criteria.where("_id").is(record.getId())),
//            update, "tests");
    }
}
