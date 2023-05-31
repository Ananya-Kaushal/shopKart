package com.example.ecommerse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderList {
    private static TableView<Order> orderTable;
    public static VBox createTable(ObservableList<Order> data)
    {
        //Columns
        TableColumn id= new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        //TableColumn price=new TableColumn("PRICE");
        //name.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity=new TableColumn("QUANTITY");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn date=new TableColumn("DATE");
        date.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        TableColumn orderStatus=new TableColumn("ORDER STATUS");
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));


        orderTable=new TableView<>();
        orderTable.getColumns().addAll(id,name,quantity,date,orderStatus);
        orderTable.setItems(data);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //the extra column will be removed with the help of Resizing policy

        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(orderTable);
        return vBox;
    }
    public static VBox getAllOrders(int cust_id, int delete_id) throws SQLException {
//        try {
//            ResultSet result =Order.getAllOrdered(cust_id,delete_id);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ObservableList<Order> data = FXCollections.observableArrayList();
//        //ObservableList<Order> data=Order.getAllOrdered(cust_id,delete_id);
//        return createTable(data);
        ResultSet result = Order.getAllOrdered(cust_id,delete_id);
        ObservableList<Order> data = FXCollections.observableArrayList();
        try{
            while(result.next()){
                Order order_product = new Order(result.getInt("id") , result.getString("name") , result.getDouble("price") , result.getInt("quantity")  ,result.getDate("order_date"), result.getString("order_status"));
                data.add(order_product);
            }
        }
        catch(SQLException e){
        }
        return createTable(data);
    }
    public Order getSelectedOrder()
    {
        return orderTable.getSelectionModel().getSelectedItem();
    }
    public VBox getOrderInCart(ObservableList<Order> data)
    {
        return createTable(data);
    }
}
