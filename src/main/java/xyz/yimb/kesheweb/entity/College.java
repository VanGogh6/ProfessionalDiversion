package xyz.yimb.kesheweb.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_college")
//@JsonIgnoreProperties(value = { "lessonVersionVocabularys", "isDeleted", "isEnabled", "handler", "hibernateLazyInitializer" })
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    private String name;

    private Integer number;

    //配置学院和学生   一对多关联查询配置
    @OneToMany(mappedBy = "college")
    private List<Student> students;


    //配置学院和专业   一对多关联查询配置
    @OneToMany(mappedBy = "college")
    private List<Major> majors;

    @Override
    public String toString() {
        return "College{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
