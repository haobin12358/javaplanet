package com.sanbinit.planet.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PasswordToHash {

    public boolean check_password_hash(String adsalt, String str_password, String adjavapassword) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        String password_salt = str_password + adsalt;
        m.update(password_salt.getBytes());
        byte[] s = m.digest();
        String hashcode = Hex.encodeHexString(s);
        return adjavapassword.equals(hashcode);
    }

    public String make_password_hash(String adsalt, String str_password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        String password_salt = str_password + adsalt;
        m.update(password_salt.getBytes());
        byte[] s = m.digest();
        return Hex.encodeHexString(s);
    }
/*
    public static void main(String args[]) throws NoSuchAlgorithmException {
        String adsalt = UUID.randomUUID().toString().replace("-", "");
        System.out.println(adsalt);
        String str_password = "123456";
        String adjavapassword = PasswordToHash.make_password_hash(adsalt, str_password);
        System.out.println(adjavapassword);
        System.out.println(PasswordToHash.check_password_hash(adsalt, str_password, adjavapassword));
    }

 */

}
