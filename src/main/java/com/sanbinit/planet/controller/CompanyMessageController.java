package com.sanbinit.planet.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sanbinit.planet.dao.model.CompanyMessage;
import com.sanbinit.planet.dao.response.Success;
import com.sanbinit.planet.service.CompanyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/club")
public class CompanyMessageController {

    //实例化CompanyMessageService
    @Autowired
    private CompanyMessageService companyMessageService;

    Success success = new Success();

    //打印用
    protected static final Logger logger = LoggerFactory.getLogger(CompanyMessageController.class);

    @GetMapping("/list")
    public PageInfo<CompanyMessage> getAll(@RequestParam int cmindex,
                                           CompanyMessage companyMessage){
        List<CompanyMessage> companyMessageList = companyMessageService.getAllCompanyMessage(companyMessage, cmindex);
        return new PageInfo<CompanyMessage>(companyMessageList);
    }

    @PostMapping("/create_message")
    public JSONObject createOne(@RequestBody CompanyMessage companyMessageResponse,
                                CompanyMessage companyMessage){
        companyMessage.setIsdelete(0);
        companyMessage.setCreatetime(new Date());
        companyMessage.setUpdatetime(new Date());
        companyMessage.setCMid(UUID.randomUUID().toString());
        companyMessage.setCMtitle(companyMessageResponse.getCMtitle());
        companyMessage.setCMindex(companyMessageResponse.getCMindex());
        companyMessage.setCMmessage(companyMessageResponse.getCMmessage());
        companyMessage.setCMreadnum(0);
        int code = companyMessageService.createOneCompanyMessage(companyMessage);
        if(code == 1){
            return success.json_success(200,0,"创建成功");
        }else{
            return success.json_success(405,405100,"创建失败");
        }
    }
}
