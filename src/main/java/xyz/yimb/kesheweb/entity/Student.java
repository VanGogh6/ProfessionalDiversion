package xyz.yimb.kesheweb.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;//主键
    private String account;//学号
    private String password;//密码
    private String name;//姓名
    private String phone;//电话
    private String wish;//志愿信息

    //配置学生和学院多对一映射
    @Transient
    private Integer cid;//学院id   标识所在学校，选专业时只能选择本学院的专业，要想选其他专业只能先转学院

    @ManyToOne(targetEntity =College.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    private College college;


    //配置学生和专业多对一映射  多个学生可以选择同一个专业
    @Transient
    private Integer mid;//专业编号
    @ManyToOne(targetEntity = Major.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Major major;

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", wish='" + wish + '\'' +
                ", cid=" + cid +
                ", mid=" + mid +
                '}';
    }
}
