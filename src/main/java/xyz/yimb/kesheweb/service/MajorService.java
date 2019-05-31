package xyz.yimb.kesheweb.service;

import xyz.yimb.kesheweb.entity.Major;

import java.util.List;

public interface MajorService {

    List<Major> getAll();

    boolean insertMajorOne(Major major);

    boolean insertMajorByCid(String name, String number, String cid);

    boolean deleteMajorByMid(Integer mid);

    Major getMajorByMid(Integer mid);

    boolean updateMjorNumberByCid(Integer number, Integer mid);

    boolean updateMjorNowNmber(Integer nownumber, Integer mid);
}
