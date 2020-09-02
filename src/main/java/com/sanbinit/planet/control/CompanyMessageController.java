package com.sanbinit.planet.control;

import com.github.pagehelper.PageInfo;
import com.sanbinit.planet.dao.model.BaseModel;
import com.sanbinit.planet.dao.model.CompanyMessage;
import com.sanbinit.planet.dao.response.Success;
import com.sanbinit.planet.service.CompanyMessageService;
import com.sanbinit.planet.util.UserToken;
import org.joda.time.DateTime;
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
    public Success createOne(@RequestBody CompanyMessage companyMessageResponse,
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
            success.setStatus(200);
            success.setMessage("创建成功");
            success.setStatus_code(200);
            return success;
        }else{
            success.setStatus(405);
            success.setStatus_code(405001);
            success.setMessage("创建失败");
            return success;
        }
    }
}
