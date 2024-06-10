package com.example.client.controller;
import com.example.client.variables.Variables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.example.client.orders.*;

public class ClientController implements Initializable {
    //Создаем экземпляр класса RestTemplate
    private RestTemplate restTemplate = new RestTemplate();
    //Создаем логгер
    private Logger logger = Logger.getLogger(ClientController.class);
    //---------------------------------------------------//
    //Элементы вкладки "Заказы"
    private String valueOfOrders;
    @FXML
    private TableView<Orders> tableViewOrders = new TableView<Orders>();
    private ObservableList<Orders> observableListOrders = FXCollections.<Orders>observableArrayList();
    @FXML
    private TableColumn<Orders, String> tableColumnID = new TableColumn<Orders, String>("ID");
    @FXML
    private TableColumn<Orders, String> tableColumnName = new TableColumn<Orders, String>("Имя");
    @FXML
    private TableColumn<Orders, String> tableColumnEmail = new TableColumn<Orders, String>("Email");
    @FXML
    private TableColumn<Orders, String> tableColumnProductName = new TableColumn<Orders, String>("Имя товара");
    @FXML
    private TableColumn<Orders, String> tableColumnPrice = new TableColumn<Orders, String>("Цена");
    @FXML
    private TableColumn<Orders, String> tableColumnCount = new TableColumn<Orders, String>("Кол-во");
    @FXML
    private Button buttonUpdateList = new Button();
    /**
     * Реализация кнопки "Обновить список заказов"
     */
    @FXML
    private void setButtonUpdateList() {
       String url_orders = "http//" + Variables.ip_server + ":" + Variables.port_server + "/getOrders";
       ResponseEntity<List<OrdersTemp>> response = restTemplate.exchange(url_orders, HttpMethod.GET, null, new ParameterizedTypeReference<List<OrdersTemp>>() {});
       observableListOrders.clear();
       for (OrdersTemp ordersTemp: response.getBody()) {
           Orders order = new Orders(String.valueOf(ordersTemp.getID()), ordersTemp.getName(), ordersTemp.getEmail(), ordersTemp.getProductName(), ordersTemp.getPrice(), ordersTemp.getCount());
           observableListOrders.add(order);
       }
    }
    @FXML
    private Button buttonProductShipped = new Button();
    /**
     * Реализация кнопки "Товар отправлен"
     */
    @FXML
    private void setButtonProductShipped() {
        String orderId = valueOfOrders;
        String url_product_shipped = "http;//" + Variables.ip_server + ":" + Variables.port_server + "/productShipped/" + orderId;
        try {
            ResponseEntity<String> response = restTemplate.exchange(url_product_shipped, HttpMethod.GET, null, String.class);
            logger.info(response.getBody());
        } catch(RuntimeException e) {
            logger.error("Сервер не доступен");
        }
    }
    //---------------------------------------------------//
    //Элементы вкладки "Добавить"
    @FXML
    private TextField textFieldProductName = new TextField();
    @FXML
    private TextField textFieldPrice = new TextField();
    @FXML
    private Button buttonCreate = new Button();
    /**
     * Реализация кнопки "Добавить товар"
     */
    @FXML
    private void setButtonCreate() {
        if (!textFieldProductName.getText().isEmpty() && !textFieldPrice.getText().isEmpty()) {
            String productName = textFieldProductName.getText();
            String price = textFieldPrice.getText();
            String url_create = "http://" + Variables.ip_server + ":" + Variables.port_server + "/create/" + productName + "&" + price;
            try {
                ResponseEntity<String> response = restTemplate.exchange(url_create, HttpMethod.GET, null, String.class);
                logger.info(response.getBody());
            } catch (RuntimeException e) {
                logger.error("Сервер не доступен");
            }
        }
    }
    //---------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Обновляем таблицу для заказов
        tableViewOrders.setItems(observableListOrders);
        tableColumnID.setCellValueFactory(cellData -> cellData.getValue().IDProperty());
        tableColumnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableColumnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tableColumnProductName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumnPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        tableColumnCount.setCellValueFactory(cellData -> cellData.getValue().countProperty());
        //Фиксируем строку в таблице для видеоматериалов
        tableViewOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                valueOfOrders = newSelection.getID();
            } catch (NullPointerException e) {
                valueOfOrders = null;
            }
        });
    }
}