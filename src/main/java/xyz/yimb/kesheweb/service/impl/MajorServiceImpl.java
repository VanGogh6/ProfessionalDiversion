package xyz.yimb.kesheweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.repository.MajorRepository;
import xyz.yimb.kesheweb.service.MajorService;

import java.util.List;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;
    @Override
    public List<Major> getAll() {
        return majorRepository.findAll();
    }

    @Override
    public boolean insertMajorOne(Major major) {
        Major save = majorRepository.save(major);
        return save!=null;
    }

    @Override
    @Transactional
    public boolean insertMajorByCid(String name, String number, String cid) {
        int i=majorRepository.insertMajor(name,number,cid);
        return i>0;
    }

    @Override
    public boolean deleteMajorByMid(Integer mid) {
        try {
            majorRepository.deleteById(mid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Major getMajorByMid(Integer mid) {
        return majorRepository.getOne(mid);
    }

    @Override
    @Transactional
    public boolean updateMjorNumberByCid(Integer number, Integer mid) {
        int i=majorRepository.updateNumber(number,mid);
        return i>0;
    }
}
