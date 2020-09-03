package com.sanbinit.planet.service;

import com.sanbinit.planet.dao.mapper.AdminMapper;
import com.sanbinit.planet.dao.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin getOneAdmin(Map<String, Object> map){
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        int i = 0;
        for(String key : map.keySet()){
            Object value = map.get(key);
            if(value == null){
                continue;
            }
            criteria.andEqualTo(key, value);
        }
        return adminMapper.selectOneByExample(example);
    }

    public int createOneAdmin(Admin admin){
        return adminMapper.insert(admin);
    }
}
