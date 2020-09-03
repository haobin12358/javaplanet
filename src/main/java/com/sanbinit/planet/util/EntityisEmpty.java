package com.sanbinit.planet.util;

import java.lang.reflect.Field;

public class EntityisEmpty {

    public boolean checkObjFieldIsNotNull(Object obj) {   // true 不为空  false 为空
        boolean flag = false;
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) == null || f.get(obj) == "") {
                } else {
                    flag = true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return flag;
    }
}
