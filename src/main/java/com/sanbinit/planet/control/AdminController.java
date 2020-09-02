package com.sanbinit.planet.control;

import com.sanbinit.planet.dao.model.Admin;
import com.sanbinit.planet.dao.response.Success;
import com.sanbinit.planet.service.AdminService;
import com.sanbinit.planet.util.PasswordToHash;
import com.sanbinit.planet.util.UserToken;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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

    @PostMapping("/admin_login")
    public Success adminLogin(@RequestBody Admin adminResponse, Admin admin) throws NoSuchAlgorithmException {
        Admin adminMessage = adminService.getOneAdmin(admin, adminResponse.getADname());
        String adjavapassword = adminMessage.getADjavapassword();
        String adsalt = adminMessage.getADsalt();
        String str_password = adminResponse.getADpassword();
        if(passwordToHash.check_password_hash(adsalt, str_password, adjavapassword)){
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject_admin = new JSONObject();
            StringBuffer adheader = new StringBuffer(adminMessage.getADheader());
            StringBuffer adlevel = new StringBuffer(adminMessage.getADlevel());
            success.setStatus(200);
            success.setStatus_code(200);
            success.setMessage("登录成功");
            return success;
        }else{
            success.setStatus(405);
            success.setStatus_code(405001);
            success.setMessage("密码错误");
            return success;
        }
    }

    @PostMapping("/add_admin_by_superadmin")
    public Success addAdmin(@RequestBody Admin adminResponse, @RequestParam String token, Admin admin){
        Map<String, String> adminMap = userToken.tokenToUser(token);
        String usid = adminMap.get("id");
        return success;
    }
}
