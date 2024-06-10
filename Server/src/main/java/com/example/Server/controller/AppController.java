package com.example.Server.controller;

import com.example.Server.db.OrdersTemp;
import com.example.Server.db.Products;
import com.example.Server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
/**
 * Контроллер для взаимодействия с приложением JavaFX
 */
@RestController
public class AppController {
    Logger logger = Logger.getLogger(AppController.class);
    @GetMapping("/application")
    private String getApp() {
        return "Приложение по продаже табачной продукции";
    }
    /**
     * Метод записывает в БД товары
     * @param product
     */
    private synchronized void writeProducts(Products product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД
            session.persist(product);
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
     * Метод вытягивает из БД список заказов
     * @return
     */
    private List<OrdersTemp> getStudents() {
        List<OrdersTemp> ordersTemps = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            ordersTemps = session.createQuery("from OrdersTemp", OrdersTemp.class).getResultList();
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return ordersTemps;
    }
    /**
     * Метод удаляет из БД заказ
     * @param ID
     */
    private synchronized void deleteOrder(int ID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            OrdersTemp ordersTemp = session.get(OrdersTemp.class, ID);
            if (ordersTemp != null) {
                //Удаляем объект
                session.delete(ordersTemp);
            }
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
