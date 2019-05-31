package xyz.yimb.kesheweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.yimb.kesheweb.entity.Student;

import java.io.InputStream;
import java.util.List;


public interface StudentService {

    /**
     * 根据学号和密码查询学生
     * @param account 学号
     * @param password 密码
     * @return 找到返回学生对象，否则null
     */
    public Student getByAccountAndPassword(String account, String password);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Student> getMajorAndCollege(Pageable pageable);

    /**
     * 单个删除
     * @param sid
     * @return
     */
    boolean deleteStuBySid(String sid);

    /**
     * 批量删除
     * @param sids
     * @return
     */
    boolean deleteBatch(String[] sids);


    boolean importData(InputStream is,String filename);

    List<Student> getAllStudent();

    Student getStuByAccount(String account);

    boolean resetPwd(Integer sid);

    Student getStuBySid(Integer sid);

    boolean updateCid(Integer sid, Integer cid);

    boolean updateMidBySid(Integer mid, Integer sid);

    boolean updateWish(String wish, Integer sid);

    boolean updNameAndPhone(String name, String phone, Integer sid);

    boolean updPwd(String pw, Integer sid);

}
