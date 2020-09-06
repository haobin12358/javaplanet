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

    //管理员登录
    @PostMapping("/admin_login")
    public JSONObject adminLogin(@RequestBody Admin adminResponse, Admin admin) throws NoSuchAlgorithmException{
        //创建用于组合搜索的map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ADname", adminResponse.getADname());
        //基于管理员名称获取管理员数据
        Admin adminMessage = adminService.getOneAdmin(map, admin);
        String adjavapassword = adminMessage.getADjavapassword();
        String adsalt = adminMessage.getADsalt();
        String str_password = adminResponse.getADpassword();
        //判断密码是否正确
        if(passwordToHash.check_password_hash(adsalt, str_password, adjavapassword)){
            //data层json
            JSONObject jsonObject = new JSONObject();
            //admin层json
            JSONObject jsonObject_admin = new JSONObject();
            StringBuffer adheader = new StringBuffer(adminMessage.getADheader());
            StringBuffer adlevel = new StringBuffer(getAdlevelName(adminMessage.getADlevel()));//利用enum转化中文
            StringBuffer adname = new StringBuffer(adminMessage.getADname());
            StringBuffer adstatus = new StringBuffer(getAdstatusName(adminMessage.getADstatus()));//利用enum转化中文
            try {
                jsonObject_admin.put("adheader", adheader);
                jsonObject_admin.put("adlevel", adlevel);
                jsonObject_admin.put("adname", adname);
                jsonObject_admin.put("adstatus", adstatus);
            }catch (JSONException e){
                logger.error("admin json error");
                throw new JSException();
            }
            //封装token
            //设置过期时间，目前为1小时
            DateTime datetime = new DateTime();
            datetime = datetime.plusHours(1);
            Date date = datetime.toDate();
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

    //利用enum转化adlevel
    public static String getAdlevelName(int index){
        for(ADlevel adlevel : ADlevel.values()){
            if(adlevel.getIndex() == index){
                return adlevel.getName();
            }
        }
        return null;
    }

    //利用enum转化adstatus
    public static String getAdstatusName(int index){
        for(ADstatus adstatus : ADstatus.values()){
            if(adstatus.getIndex() == index){
                return adstatus.getName();
            }
        }
        return null;
    }

    //创建管理员
    @PostMapping("/add_admin_by_superadmin")
    public JSONObject addAdmin(@RequestBody Admin adminResponse, @RequestParam String token, Admin admin) throws NoSuchAlgorithmException {
        //判断是否是超级管理员
        if(!userToken.isSuperAdmin(token)){
            return success.json_success(405, 405002, "无权限");
        }
        //创建用于组合搜索的map
        Map<String, Object> map = new HashMap<String, Object>();
        //创建admin获取对象
        Admin adminMessage;
        //两种场景的判断
        map.put("ADname", adminResponse.getADname());
        adminMessage = adminService.getOneAdmin(map, admin);
        if(entityisEmpty.checkObjFieldIsNotNull(adminMessage)){
            return success.json_success(405, 405003, "用户名重复");
        }
        map.remove("ADname");
        map.put("ADtelphone", adminResponse.getADtelphone());
        adminMessage = adminService.getOneAdmin(map, admin);
        if(entityisEmpty.checkObjFieldIsNotNull(adminMessage)){
            return success.json_success(405, 405004, "手机号重复");
        }
        //设置写入的数据entity内容
        admin.setIsdelete(0);
        admin.setCreatetime(new Date());
        admin.setUpdatetime(new Date());
        admin.setADname(adminResponse.getADname());
        admin.setADheader(adminResponse.getADheader());
        admin.setADlevel(2);
        admin.setADnum(0);
        admin.setADid(UUID.randomUUID().toString());
        String adsalt = UUID.randomUUID().toString();//加密盐值
        admin.setADsalt(adsalt);
        String adjavapassword = passwordToHash.make_password_hash(adsalt, adminResponse.getADpassword());//加密
        admin.setADjavapassword(adjavapassword);
        admin.setADstatus(0);
        admin.setADtelphone(adminResponse.getADtelphone());
        admin.setADpassword(adjavapassword);
        //写入
        int code = adminService.createOneAdmin(admin);
        //TODO 写入成功返回1，写入失败返回情况未知
        if(code == 1){
            return success.json_success(200, 0, "创建成功");
        }else{
            return success.json_success(405, 405005, "创建失败");
        }

    }

    //更新管理员信息
    @PostMapping("/update_admin")
    public JSONObject updateAdmin(@RequestBody JSONObject jsonObject, @RequestParam String token, Admin admin) throws NoSuchAlgorithmException {
        //判断是否为超级管理员
        if(!userToken.isSuperAdmin(token)){
            return success.json_success(405, 405002, "无权限");
        }
        String adid = jsonObject.getString("adid");
        Map<String, Object> map = new HashMap<>();
        map.put("ADid", adid);
        //判断两次密码是否相同
        if(!jsonObject.getString("adpassword").equals(jsonObject.getString("adpasswordagain"))){
            return success.json_success(405, 405006, "两次密码输入不同");
        }
        //设置要更新的内容
        admin.setUpdatetime(new Date());
        admin.setADheader(jsonObject.getString("adheader"));
        admin.setADname(jsonObject.getString("adname"));
        admin.setADid(adid);
        String adsalt = UUID.randomUUID().toString();
        admin.setADsalt(adsalt);
        String javapassword = passwordToHash.make_password_hash(adsalt, jsonObject.getString("adpassword"));
        admin.setADpassword(javapassword);
        admin.setADjavapassword(javapassword);
        //TODO 判断手机号是否存在
        admin.setADtelphone(jsonObject.getString("adtelphone"));

        int code = adminService.updateOneAdmin(map, admin);
        if(code == 1){
            return success.json_success(200, 0, "更新成功");
        }else{
            return success.json_success(405, 405007, "更新失败");
        }

    }

    //管理员更新密码
    @PostMapping("/update_admin_password")
    public JSONObject updatePassword(@RequestBody JSONObject jsonObject, @RequestParam String token){
        return success.json_success(200, 0, "更新成功");
    }
}
