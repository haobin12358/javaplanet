package com.sanbinit.planet.dao.response;

import com.alibaba.fastjson.JSONObject;

public class Success {

    public JSONObject json_success(int status, int status_code, String message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        if(status_code != 0){
            jsonObject.put("status_code", status_code);
        }
        jsonObject.put("message", message);

        return jsonObject;
    }
}
