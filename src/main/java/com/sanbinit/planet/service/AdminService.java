package com.sanbinit.planet.service;

import com.sanbinit.planet.dao.mapper.AdminMapper;
import com.sanbinit.planet.dao.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin getOneAdmin(Admin admin, String adname){
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ADname", adname);
        return adminMapper.selectOneByExample(example);
    }
}
