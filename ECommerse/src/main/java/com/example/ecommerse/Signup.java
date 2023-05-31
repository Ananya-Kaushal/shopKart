package com.example.ecommerse;

import java.security.NoSuchAlgorithmException;

public class Signup {
    public static boolean customerLogin(Customer customer) throws NoSuchAlgorithmException {
        byte[] bytePassword=Encryption.getSHA(customer.getPassword());
        String encryptedPassword=Encryption.toHexString(bytePassword);
        DbConnection dbConnection = new DbConnection();
        String name= customer.getName();
        String userpassword= encryptedPassword;
        String email= customer.getEmail();
        String mobile= customer.getMobile();
        String query;
        query = "INSERT INTO customer(name,email,mobile,password) VALUES('"+name+"','"+email+"','"+mobile+"','"+userpassword+"')";
        int rs = dbConnection.updateDatabase(query);
        return rs != 0;
    }
}
