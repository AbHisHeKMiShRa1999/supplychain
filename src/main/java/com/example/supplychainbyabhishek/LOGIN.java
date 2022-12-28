package com.example.supplychainbyabhishek;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class LOGIN {
    private byte[] getSHA(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String getEncryptedPassword(String password){
        try{
            BigInteger number = new BigInteger(1,getSHA(password));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean coustomerLogin(String email,String password){

        String query = String.format("SELECT * FROM coustomer WHERE email = '%s' AND password = '%s'",email,password);
        try{
            DATABASECONNECTOR dbCon = new DATABASECONNECTOR();
            ResultSet rs = dbCon.getQueryTable(query);
            if(rs != null && rs.next()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args){
        LOGIN login = new LOGIN();
        System.out.println(login.coustomerLogin("am86373@gmail.com","Abhishek"));
    }
}
