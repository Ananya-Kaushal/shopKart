package com.example.ecommerse;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public Customer customerLogin(String userName,String password) throws NoSuchAlgorithmException {
        byte[] bytePassword=Encryption.getSHA(password);
        String encryptedPassword=Encryption.toHexString(bytePassword);
        String loginQuery="select * from customer where email='"+userName+"' and password='"+encryptedPassword+"'";
        DbConnection conn=new DbConnection();
        ResultSet rs= conn.getQueryTable(loginQuery);
        try {
            if (rs.next())
            {
                return new Customer(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("mobile"),rs.getString("address"));
            }
        }/*catch (Exception e)
        {
            e.printStackTrace();
        }*/
        catch (SQLException e)
        {
        }
        return null;
    }

    public static void updateCustomerEmail (String userName,String password, String email) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String loginQuery="select id from customer where email='"+userName+"' and password='"+password+"'";
        DbConnection conn=new DbConnection();
        ResultSet rs= conn.getQueryTable(String.valueOf(loginQuery));
        int id= Integer.parseInt(String.valueOf(loginQuery));
        try {
            if (rs.next()) {
                String updateStmt =
                        "BEGIN\n" +
                                "   UPDATE customer\n" +
                                "      SET EMAIL = '" + email + "'\n" +
                                "    WHERE ID = " + id + ";\n" +
                                "   COMMIT;\n" +
                                "END;";
                //Execute UPDATE operation
                conn.updateDatabase(updateStmt);
            }
        }catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    public static void deleteCustomer (String userName,String password,String email) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String loginQuery="select id from customer where email='"+userName+"' and password='"+password+"'";
        DbConnection conn=new DbConnection();
        ResultSet rs= conn.getQueryTable(String.valueOf(loginQuery));
        int id=Integer.parseInt(loginQuery);
        try {
            if (rs.next()) {
                String updateStmt =
                        "BEGIN\n" +
                                "   DELETE FROM CUSTOMER\n" +
                                "         WHERE name ="+ userName  + "And email="+ email+";\n";
                                //"   COMMIT;\n" +
                                //"END;";
                //Execute UPDATE operation
                conn.updateDatabase(updateStmt);
            }
        }catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Login login=new Login();
        //Customer customer=login.customerLogin("angad@gmail.com","abc123");
        //System.out.println("Welcome : "+customer.getName());
        //byte[] bytePassword=Encryption.getSHA("abc456");
        //String encryptedPassword=Encryption.toHexString(bytePassword);
        //System.out.println(encryptedPassword);

    }
}
