package com.example.supplychainbyabhishek;

public class order {
    public static boolean placeOrder(String coustomeremail,product product){
        DATABASECONNECTOR databaseconnector = new DATABASECONNECTOR();
        String query = String.format("INSERT INTO orders (coustomerid,productid) VALUES ((SELECT coustomerid FROM coustomer WHERE email = '%s'),%s);",coustomeremail,product.getId());
        int rowCount =0;
        try{
            rowCount = databaseconnector.executeUpdateQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowCount!=0;
    }


}
