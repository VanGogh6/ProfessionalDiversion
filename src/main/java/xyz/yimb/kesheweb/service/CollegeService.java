package xyz.yimb.kesheweb.service;

import xyz.yimb.kesheweb.entity.College;

import java.util.List;

public interface CollegeService {
    List<College> getAll();

    boolean deleteCollege(Integer cid);

    boolean insertCollege(College college);

    College getCollegeByCid(Integer cid);
}
