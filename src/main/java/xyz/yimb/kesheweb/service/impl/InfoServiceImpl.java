package xyz.yimb.kesheweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yimb.kesheweb.entity.Info;
import xyz.yimb.kesheweb.repository.InfoRepository;
import xyz.yimb.kesheweb.service.InfoService;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Info> getAll() {
        return infoRepository.findAll();
    }

    @Override
    public boolean save(Info info) {
        Info save = infoRepository.save(info);
        return save!=null;
    }
}
