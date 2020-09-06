package com.sanbinit.planet.service;

import com.sanbinit.planet.dao.mapper.AdminMapper;
import com.sanbinit.planet.dao.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

@Service
public class AdminService extends BaseService{

    @Autowired
    private AdminMapper adminMapper;

    public Admin getOneAdmin(Map<String, Object> map, Admin admin){
        Example example = new Example(admin.getClass());
        example = getExample(map, example);
        return adminMapper.selectOneByExample(example);
    }

    public int createOneAdmin(Admin admin){
        return adminMapper.insert(admin);
    }

    public int updateOneAdmin(Map<String, Object> map, Admin admin){
        Example example = new Example(admin.getClass());
        example = getExample(map, example);
        return adminMapper.updateByExampleSelective(admin, example);
    }
}
