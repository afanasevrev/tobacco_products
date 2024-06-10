package com.example.Server.controller;

import com.example.Server.db.OrdersTemp;
import com.example.Server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Контроллер для взаимодействия с клиентами по веб - браузеру
 */
@Controller
public class WebController {
    /**
     * Главная страница
     * @return
     */
    @GetMapping("/")
    private String getMainPage() {
        return "main";
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
}
