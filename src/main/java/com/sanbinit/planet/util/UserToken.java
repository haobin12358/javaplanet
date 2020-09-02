package com.sanbinit.planet.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserToken {

    private static final String SECRET = "9a96349e2345385785e804e0f4254dee";

    private static String ISSUER = "sanbinit";

    Logger logger = LoggerFactory.getLogger(UserToken.class);

    public String usidtoToken(String usid, String model, String level, String username, Date overdate){

        Map<String, Object> map = new HashMap<>();
        map.put("id", usid);
        map.put("model", model);
        map.put("level", level);
        map.put("username", username);
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            //创建jwt
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(overdate);

            //传入参数
            map.forEach((key, value)->{
                builder.withClaim(key, (String) value);
            });
            return builder.sign(algorithm);
        }catch(IllegalArgumentException | UnsupportedEncodingException e){
            //logger.error("jwt error");
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> tokenToUser(String token){
        Algorithm algorithm = null;
        try{
            algorithm = Algorithm.HMAC256(SECRET);
        }catch (IllegalArgumentException | UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<>();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

}
