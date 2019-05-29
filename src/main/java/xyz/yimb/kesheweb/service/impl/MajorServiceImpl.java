package xyz.yimb.kesheweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yimb.kesheweb.entity.Major;
import xyz.yimb.kesheweb.repository.MajorRepository;
import xyz.yimb.kesheweb.service.MajorService;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;
    @Override
    public List<Major> getAll() {
        return majorRepository.findAll();
    }
}
