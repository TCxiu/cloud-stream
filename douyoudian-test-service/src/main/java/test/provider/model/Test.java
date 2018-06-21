package test.provider.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 创建者: Administrator
 * @createTime 创建时间:2018-06-15
 */

@Document(collection = "tests")
public class Test implements Serializable {
    /**	主键	*/
    @Id
    private String id;

    /**	钱	*/
    private BigDecimal money;

    /**	版本	*/
    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", money=" + money +
                ", version=" + version +
                '}';
    }
}