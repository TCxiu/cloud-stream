package test.provider.controller;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import test.provider.Service.SendMsgService;
import test.provider.model.Test;
import java.util.List;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 09:49
 * @Description 类描述:
 */

@RestController
@RequestMapping("test")
public class TestController {


    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SendMsgService sendMsgService;

    @GetMapping("")
    public Object list(){
        List<Test> tests = mongoTemplate.findAll(Test.class, "tests");
        return tests;
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable String id){
        Test test = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Test.class, "tests");
        return test;
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id,@RequestBody Test record){

        record.setId(id);

        sendMsgService.sendMsg(record);
        return "ok";
    }

    @PostMapping("")
    public Object add(@RequestBody Test test){
        mongoTemplate.save(test,"tests");
        return test.getId();
    }
}
