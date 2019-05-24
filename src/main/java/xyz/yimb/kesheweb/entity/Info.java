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
    private String id;
    private long uid;
    private String loginTime;
}
