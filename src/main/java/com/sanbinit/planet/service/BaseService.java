package com.sanbinit.planet.service;

import tk.mybatis.mapper.entity.Example;

import java.util.Map;

public class BaseService {

    public Example getExample(Map<String, Object> map, Example example){
        Example.Criteria criteria = example.createCriteria();
        //int i = 0;
        for(String key : map.keySet()){
            Object value = map.get(key);
            if(value == null){
                continue;
            }
            criteria.andEqualTo(key, value);
        }
        return example;
    }


}
