package test.provider.listener;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import test.provider.model.Test;
import test.provider.mq.TestInPut;
import java.util.concurrent.locks.ReentrantLock;



/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 11:47
 * @Description 类描述:
 */

@EnableBinding({TestInPut.class})
public class TestListener {

    @Autowired
    MongoTemplate mongoTemplate;
    private ReentrantLock lock = new ReentrantLock();


    @StreamListener(TestInPut.INPUT)
    public void record(Test record) {
        lock.lock();

            Test test = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(record.getId())), Test.class, "tests");
            test.setMoney(test.getMoney().add(record.getMoney().negate()));
            test.setVersion(test.getVersion()+1);
            Update update = new Update();
            update.set("_id",test.getId())
                    .set("_class",test.getClass().getName())
                    .set("version",test.getVersion())
                    .set("money",test.getMoney());

            WriteResult writeResult = mongoTemplate.updateMulti(
                    Query.query(Criteria.where("_id").is(record.getId())),
                    update, "tests");

        lock.unlock();
    }
}
