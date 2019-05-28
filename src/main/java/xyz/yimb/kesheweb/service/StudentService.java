package xyz.yimb.kesheweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
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
}
