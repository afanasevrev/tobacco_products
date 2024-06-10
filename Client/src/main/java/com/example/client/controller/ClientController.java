package com.example.client.controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.web.client.RestTemplate;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.client.orders.Orders;

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
       // String
    }
    @FXML
    private Button buttonProductShipped = new Button();
    //---------------------------------------------------//
    //Элементы вкладки "Добавить"
    @FXML
    private TextField textFieldProductName = new TextField();
    @FXML
    private TextField textFieldPrice = new TextField();
    @FXML
    private Button buttonCreate = new Button();
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