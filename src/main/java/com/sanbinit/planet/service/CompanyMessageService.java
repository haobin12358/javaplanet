package com.sanbinit.planet.service;

import com.sanbinit.planet.dao.mapper.CompanyMessageMapper;
import com.sanbinit.planet.dao.model.CompanyMessage;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyMessageService {

    @Autowired
    private CompanyMessageMapper companyMessageMapper;

    public List<CompanyMessage> getAllCompanyMessage(CompanyMessage companymessage, int cmindex){
        companymessage.setCMindex(cmindex);
        return companyMessageMapper.select(companymessage);
    }

    public int createOneCompanyMessage(CompanyMessage companyMessage){
        return companyMessageMapper.insert(companyMessage);
    }
}
