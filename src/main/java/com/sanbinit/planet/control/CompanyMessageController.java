package com.sanbinit.planet.control;

import com.github.pagehelper.PageInfo;
import com.sanbinit.planet.dao.model.CompanyMessage;
import com.sanbinit.planet.service.CompanyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO 待封装
@RestController
@RequestMapping("api/v2/club")
public class CompanyMessageController {


    //实例化CompanyMessageService
    @Autowired
    private CompanyMessageService companyMessageService;

    //打印用
    protected static final Logger logger = LoggerFactory.getLogger(CompanyMessageController.class);

    @GetMapping("/list")
    public PageInfo<CompanyMessage> getAll(@RequestParam int cmindex, CompanyMessage companyMessage){
        List<CompanyMessage> companyMessageList = companyMessageService.getAllCompanyMessage(companyMessage, cmindex);
        //logger.error(Integer.toString(cmindex));
        return new PageInfo<CompanyMessage>(companyMessageList);
    }


}
