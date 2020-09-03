package com.sanbinit.planet.control;

import com.sanbinit.planet.dao.Enum.ADlevel;
import com.sanbinit.planet.dao.Enum.ADstatus;
import com.sanbinit.planet.dao.model.Admin;
import com.sanbinit.planet.dao.response.Success;
import com.sanbinit.planet.service.AdminService;
import com.sanbinit.planet.util.EntityisEmpty;
import com.sanbinit.planet.util.PasswordToHash;
import com.sanbinit.planet.util.UserToken;
import netscape.javascript.JSException;
import org.aspectj.bridge.IMessage;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONException;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    UserToken userToken = new UserToken();

    PasswordToHash passwordToHash = new PasswordToHash();

    Success success = new Success();

    EntityisEmpty entityisEmpty = new EntityisEmpty();

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/admin_login")
    public JSONObject adminLogin(@RequestBody Admin adminResponse) throws NoSuchAlgorithmException{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ADname", adminResponse.getADname());
        Admin admin = adminService.getOneAdmin(map);
        String adjavapassword = admin.getADjavapassword();
        String adsalt = admin.getADsalt();
        String str_password = adminResponse.getADpassword();
        if(passwordToHash.check_password_hash(adsalt, str_password, adjavapassword)){
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject_admin = new JSONObject();
            StringBuffer adheader = new StringBuffer(admin.getADheader());
            StringBuffer adlevel = new StringBuffer(getAdlevelName(admin.getADlevel()));
            StringBuffer adname = new StringBuffer(admin.getADname());
            StringBuffer adstatus = new StringBuffer(getAdstatusName(admin.getADstatus()));
            try {
                jsonObject_admin.put("adheader", adheader);
                jsonObject_admin.put("adlevel", adlevel);
                jsonObject_admin.put("adname", adname);
                jsonObject_admin.put("adstatus", adstatus);
            }catch (JSONException e){
                logger.error("admin json error");
                throw new JSException();
            }
            DateTime datetime = new DateTime();
            datetime = datetime.plusHours(1);
            Date date = datetime.toDate();
            String token_str = userToken.usidtoToken(
                    admin.getADid(),
                    "Admin",
                    Integer.toString(admin.getADlevel()),
                    admin.getADname(),
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
    public JSONObject addAdmin(@RequestBody Admin adminResponse, @RequestParam String token, Admin admin) throws NoSuchAlgorithmException {
        Map<String, String> tokenMap = userToken.tokenToUser(token);
        String adid = tokenMap.get("id");
        String model = tokenMap.get("model");
        if(!model.equals("Admin")){
            return success.json_success(405, 405002, "无权限");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ADid", adid);
        Admin adminMessage;
        adminMessage = adminService.getOneAdmin(map);
        if(adminMessage.getADlevel() != 1){
            return success.json_success(405, 405002, "无权限");
        }
        map.remove("ADid");
        map.put("ADname", adminResponse.getADname());
        adminMessage = adminService.getOneAdmin(map);
        if(entityisEmpty.checkObjFieldIsNotNull(adminMessage)){
            return success.json_success(405, 405003, "用户名重复");
        }
        map.remove("ADname");
        map.put("ADtelphone", adminResponse.getADtelphone());
        adminMessage = adminService.getOneAdmin(map);
        if(entityisEmpty.checkObjFieldIsNotNull(adminMessage)){
            return success.json_success(405, 405004, "手机号重复");
        }
        admin.setIsdelete(0);
        admin.setCreatetime(new Date());
        admin.setUpdatetime(new Date());
        admin.setADname(adminResponse.getADname());
        admin.setADheader(adminResponse.getADheader());
        admin.setADlevel(2);
        admin.setADnum(0);
        admin.setADid(UUID.randomUUID().toString());
        String adsalt = UUID.randomUUID().toString();
        admin.setADsalt(adsalt);
        String adjavapassword = passwordToHash.make_password_hash(adsalt, adminResponse.getADpassword());
        admin.setADjavapassword(adjavapassword);
        admin.setADstatus(0);
        admin.setADtelphone(adminResponse.getADtelphone());
        admin.setADpassword(adjavapassword);
        int code = adminService.createOneAdmin(admin);
        if(code == 1){
            return success.json_success(200, 0, "创建成功");
        }else{
            return success.json_success(405, 405005, "创建失败");
        }

    }
}
