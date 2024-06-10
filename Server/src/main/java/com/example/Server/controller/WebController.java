package com.example.Server.controller;

import com.example.Server.db.OrdersTemp;
import com.example.Server.db.Products;
import com.example.Server.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для взаимодействия с клиентами по веб - браузеру
 */
@Controller
public class WebController {
    private Logger logger = Logger.getLogger(WebController.class);
    private String price;
    private String productName;
    /**
     * Главная страница
     * @return
     */
    @GetMapping("/")
    private String getMainPage(Model model) {
        List<Products> products = getProducts();
        model.addAttribute("products", products);
        return "main";
    }
    /**
     * Переходим по ссылке и оставляем заявку на получение товара
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    private String getDetails(Model model, @PathVariable("id") int id) {
        Products product = getProduct(id);
        price = product.getPrice();
        productName = product.getProductName();
        model.addAttribute("selectedProduct", product);
        model.addAttribute("ordersTemp", new OrdersTemp());
        return "details";
    }
    /**
     * Записываем в БД полученную заявку, далее оно должно отображаться в приложении администратора
     * @param ordersTemp
     * @return
     */
    @PostMapping("/order")
    private String setOrders(@ModelAttribute OrdersTemp ordersTemp) {
        ordersTemp.setPrice(price);
        ordersTemp.setProductName(productName);
        writeOrdersTemp(ordersTemp);
        price = "";
        productName = "";
        return "orders";
    }
    /**
     * Метод записывает в БД заказы
     * @param ordersTemp
     */
    private synchronized void writeOrdersTemp(OrdersTemp ordersTemp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД
            session.persist(ordersTemp);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод вытягивает из БД продукт
     * @param id
     */
    private Products getProduct(int id) {
        Products product = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            product = session.get(Products.class, id);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
    }
    /**
     * Метод вытягивает из БД список товаров
     * @return
     */
    private List<Products> getProducts() {
        List<Products> products = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            products = session.createQuery("from Products", Products.class).getResultList();
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return products;
    }
}
