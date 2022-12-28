package com.example.supplychainbyabhishek;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productdetails {
    public TableView<product>productTable;
    public Pane getAllProducts() {

        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<product> products = product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(com.example.supplychainbyabhishek.SupplyChain.width, com.example.supplychainbyabhishek.SupplyChain.height);

        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Pane tablePane = new Pane();
        //gridPane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.setMinSize(com.example.supplychainbyabhishek.SupplyChain.width, com.example.supplychainbyabhishek.SupplyChain.height);
        tablePane.getChildren().addAll(productTable);

        return tablePane;
    }

    public Pane getproductsByName(String productname){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

     //   ObservableList<product>data = FXCollections.observableArrayList();
       // data.add(new product(1,"lenovo",23456));
        //data.add(new product(1,"HP",34567));
        ObservableList<product> products = product.getproductsByName(productname);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(com.example.supplychainbyabhishek.SupplyChain.width, com.example.supplychainbyabhishek.SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        Pane tablePane = new Pane();
       // tablePane.setStyle("-fx-background-color: #COCOCO");
        tablePane.setMinSize(com.example.supplychainbyabhishek.SupplyChain.width, com.example.supplychainbyabhishek.SupplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;

    }
    public product getSelectedProduct(){
        try {
            product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
