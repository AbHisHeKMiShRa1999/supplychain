package com.example.supplychainbyabhishek;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
    public static final int width = 700,height = 600,headerbar = 50;
    Pane BodyPane = new Pane();
    //public static int bodywidth,bodyheight;
    LOGIN login = new LOGIN();
    productdetails productdetails = new productdetails();
    Button globallogin;
    Label coustomerEmail = null;
    String coustomeremail = null;

    private GridPane headerbar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productname = searchText.getText();
                BodyPane.getChildren().clear();
                BodyPane.getChildren().add(productdetails.getproductsByName(productname));
            }
        });
        globallogin = new Button("Login");
        globallogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BodyPane.getChildren().clear();
                BodyPane.getChildren().add(loginpage());
                globallogin.setDisable(true);



            }
        });
        coustomerEmail = new Label("hello user");

        GridPane gridPane= new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(BodyPane.getMinWidth(),headerbar-10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(globallogin,2,0);
        gridPane.add(coustomerEmail,3,0);

        return gridPane;

    }
    private GridPane loginpage(){

        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label("i am message");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                // messageLabel.setText(email+"$$" + password);
                if (login.coustomerLogin(email, password)) {
                    messageLabel.setText("Login Successful");
                    coustomeremail = email;
                    globallogin.setDisable(true);
                    coustomerEmail.setText("WELCOME :" + coustomeremail);
                    BodyPane.getChildren().clear();
                    BodyPane.getChildren().add(productdetails.getAllProducts());



                } else {
                    messageLabel.setText("login failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(BodyPane.getMinWidth(),BodyPane.getMinHeight());

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //gridPane.setStyle("-fx-background-color: #COCOCO");
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,0,2);
        gridPane.add(messageLabel,1,2);

        return gridPane;


    }
    private GridPane footerbar() {


        Button addtocartButton = new Button("Add To Cart");
        Button BuynowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        BuynowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedProduct = productdetails.getSelectedProduct();
                if(order.placeOrder(coustomeremail,selectedProduct)){
                    messageLabel.setText("Ordered");
                }
                else{
                    messageLabel.setText("Order Failed");
                }

            }
        });
        GridPane gridPane= new GridPane();
        gridPane.setMinSize(BodyPane.getMinWidth(),headerbar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        gridPane.setTranslateY(headerbar+height+5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(addtocartButton,0,0);
        gridPane.add(BuynowButton,1,0);
        gridPane.add(messageLabel,2,0);


        return gridPane;

    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+2*headerbar);

        BodyPane.setMinSize(width,height);
        BodyPane.setTranslateY(headerbar);
        BodyPane.getChildren().addAll(productdetails.getAllProducts());

        root.getChildren().addAll(headerbar(),BodyPane,footerbar());
        return root;

    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}