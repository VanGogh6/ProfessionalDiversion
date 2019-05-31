package xyz.yimb.kesheweb.service;

import xyz.yimb.kesheweb.entity.Info;

import java.util.List;

public interface InfoService {

    List<Info> getAll();

    boolean save(Info info);
}
