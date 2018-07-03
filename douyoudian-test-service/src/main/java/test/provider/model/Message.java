package test.provider.model;

import javax.persistence.*;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/07/02 15:20
 * @Description 类描述:
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id",columnDefinition = "bigint COMMENT  '消息Id主键'")
    private Long id;

    @Column(name = "message_status",columnDefinition = "varchar(20) COMMENT  '消息状态'")
    private String status;

    @Column(name = "message_data",columnDefinition = "Json COMMENT  '消息内容'")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", status=" + status +
                ", message=" + message +
                '}';
    }
}
