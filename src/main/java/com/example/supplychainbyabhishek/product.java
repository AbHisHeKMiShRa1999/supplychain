package com.example.supplychainbyabhishek;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;
import java.sql.ResultSet;

public class product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    public double getPrice() {
        return price.get();
    }
    public String getName() {
        return name.get();
    }
    public int getId() {
        return id.get();
    }
    public product(int id,String name,double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    public static ObservableList<product> getAllProducts() {

        DATABASECONNECTOR dataBaseConnection = new DATABASECONNECTOR();
        ObservableList<product> productList = FXCollections.observableArrayList();
        String selectProducts = "SELECT * FROM product";
        try{
            ResultSet rs = dataBaseConnection.getQueryTable(selectProducts);
            while(rs.next()){
                productList.add(
                        new product(
                                rs.getInt("product_id"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
    public static ObservableList<product> getproductsByName(String productname){
        DATABASECONNECTOR databaseconnector = new DATABASECONNECTOR();
        ObservableList<product> productList = FXCollections.observableArrayList();
        String selectproduct = String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%'",productname.toLowerCase());
        try {
            ResultSet rs = databaseconnector.getQueryTable(selectproduct);
            while (rs.next()){
                productList.add(new product(rs.getInt("productid"),
                        rs.getString("name"),rs.getDouble("price")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
