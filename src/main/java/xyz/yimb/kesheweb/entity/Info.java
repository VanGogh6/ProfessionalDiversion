package xyz.yimb.kesheweb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_info")
public class Info {
    @Id
    private Integer id;
    private Integer uid;
    private String loginTime;

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", uid=" + uid +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
