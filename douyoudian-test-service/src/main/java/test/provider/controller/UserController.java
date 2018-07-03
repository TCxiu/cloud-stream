package test.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.provider.Service.UserService;
import test.provider.model.User;
import java.util.List;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/15 09:49
 * @Description 类描述:
 */

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public Object list(){
        List<User> users = userService.listUser();
        return users;
    }

    @GetMapping("/{id}")
    public Object one(@PathVariable Long id){
        User user = userService.finduserById(id);
        return user;
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Long id,@RequestBody User record){

        boolean result = userService.updateUser(id, record);

        return result;
    }

    @PostMapping("")
    public Object add(@RequestBody User record){
        boolean result = userService.addUser(record);
        return result;
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id){
        userService.delUser(id);
    }
}
