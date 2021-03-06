package xyz.yimb.kesheweb.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;//编号主键
    private String name;//专业名称名
    private Integer number;//专业招收总人数
    private Integer nownumber;//实际招收人数
    @Transient
    private Integer cid;//学院id


    //配置多对一映射  fetch = FetchType.LAZY (懒加载)  这儿是维护方
    @ManyToOne(targetEntity = College.class,fetch = FetchType.LAZY)//多个专业对应一个学院
    @JoinColumn(name = "cid")//指定关联字段
    private College college;

    //配置一对多映射
    @OneToMany(mappedBy = "major")
    private List<Student> students;

    @Override
    public String toString() {
        return "Major{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", nownumber=" + nownumber +
                ", cid=" + cid +
                '}';
    }
}
