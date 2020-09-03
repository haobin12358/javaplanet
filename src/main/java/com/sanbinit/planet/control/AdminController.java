package com.sanbinit.planet.control;

import com.sanbinit.planet.dao.Enum.ADlevel;
import com.sanbinit.planet.dao.Enum.ADstatus;
import com.sanbinit.planet.dao.model.Admin;
import com.sanbinit.planet.dao.response.Success;
import com.sanbinit.planet.service.AdminService;
import com.sanbinit.planet.util.PasswordToHash;
import com.sanbinit.planet.util.UserToken;
import netscape.javascript.JSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    UserToken userToken = new UserToken();

    PasswordToHash passwordToHash = new PasswordToHash();

    Success success = new Success();

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    EnumMap aDlevelEnum = new EnumMap(ADlevel.class);

    @PostMapping("/admin_login")
    public JSONObject adminLogin(@RequestBody Admin adminResponse, Admin admin) throws NoSuchAlgorithmException{
        Admin adminMessage = adminService.getOneAdmin(admin, adminResponse.getADname());
        String adjavapassword = adminMessage.getADjavapassword();
        String adsalt = adminMessage.getADsalt();
        String str_password = adminResponse.getADpassword();
        if(passwordToHash.check_password_hash(adsalt, str_password, adjavapassword)){
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject_admin = new JSONObject();
            StringBuffer adheader = new StringBuffer(adminMessage.getADheader());
            StringBuffer adlevel = new StringBuffer(getAdlevelName(adminMessage.getADlevel()));
            StringBuffer adname = new StringBuffer(adminMessage.getADname());
            StringBuffer adstatus = new StringBuffer(getAdstatusName(adminMessage.getADstatus()));
            try {
                jsonObject_admin.put("adheader", adheader);
                jsonObject_admin.put("adlevel", adlevel);
                jsonObject_admin.put("adname", adname);
                jsonObject_admin.put("adstatus", adstatus);
            }catch (JSONException e){
                logger.error("admin json error");
                throw new JSException();
            }
            Date date = new Date();
            date.setTime(1000*60*60);
            String token_str = userToken.usidtoToken(
                    adminMessage.getADid(),
                    "Admin",
                    Integer.toString(adminMessage.getADlevel()),
                    adminMessage.getADname(),
                    date);
            StringBuffer token = new StringBuffer(token_str);
            try {
                jsonObject.put("admin", jsonObject_admin);
                jsonObject.put("token", token);
            }catch (JSONException e){
                logger.error("data json error");
                throw new JSException();
            }
            JSONObject response = success.json_success(200, 0, "登录成功");
            response.put("data", jsonObject);
            return response;
        }else{
            return success.json_success(405, 405001, "密码错误");
        }
    }

    public static String getAdlevelName(int index){
        for(ADlevel adlevel : ADlevel.values()){
            if(adlevel.getIndex() == index){
                return adlevel.getName();
            }
        }
        return null;
    }

    public static String getAdstatusName(int index){
        for(ADstatus adstatus : ADstatus.values()){
            if(adstatus.getIndex() == index){
                return adstatus.getName();
            }
        }
        return null;
    }

    @PostMapping("/add_admin_by_superadmin")
    public JSONObject addAdmin(@RequestBody Admin adminResponse, @RequestParam String token, Admin admin){
        Map<String, String> adminMap = userToken.tokenToUser(token);
        String usid = adminMap.get("id");
        return success.json_success(200, 0, "创建成功");
    }
}
