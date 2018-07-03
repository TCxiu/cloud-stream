package test.provider.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/07/02 13:37
 * @Description 类描述:
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id",columnDefinition = "bigint COMMENT  '用户Id主键'")
    private Long id;

    @Column(name = "user_name",columnDefinition = "varchar(255) COMMENT  '用户名'")
    private String name ;

    @Column(name = "user_age",columnDefinition = "int(4) COMMENT  '用户年龄'")
    private int age;

    @Column(name = "user_create_time",columnDefinition = "DATETIME COMMENT  '用户创建日期'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "user_update_time",columnDefinition = "DATETIME COMMENT  '用户更新日期'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "user_money",columnDefinition = "Decimal(65,30)  COMMENT '用户存款'")
    private BigDecimal money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", money=" + money +
                '}';
    }
}
