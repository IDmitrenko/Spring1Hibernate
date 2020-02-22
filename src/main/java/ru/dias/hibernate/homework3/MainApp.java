package ru.dias.hibernate.homework3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.dias.hibernate.homework3.entities.Customer;
import ru.dias.hibernate.homework3.entities.Product;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = null;
        try {
            // Список товаров клиента с id = 3
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customer = session.get(Customer.class, 3L);
            for (Product p : customer.getProducts()) {
                System.out.println(p);
            }
            session.getTransaction().commit();

            // Список клиентов, купивших товар с id = 8
            session = null;
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product = session.get(Product.class, 8L);
            for (Customer c : product.getCustomers()) {
                System.out.println(c);
            }
            session.getTransaction().commit();

            // удаление клиента с id = 4 и его покупок
            session = null;
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customer4 = session.get(Customer.class, 4L);
            session.remove(customer4);
            customer4.getProducts().clear();
            session.getTransaction().commit();

            // удаление товара с id = 8 и списка клиентов, купивших его
            session = null;
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product8 = session.get(Product.class, 8L);
            session.delete(product8);
            product8.getCustomers().clear();
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
