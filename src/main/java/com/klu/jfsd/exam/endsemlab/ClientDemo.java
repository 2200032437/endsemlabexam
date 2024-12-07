package com.klu.jfsd.exam.endsemlab;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        // Open session and begin transaction
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // HQL Update Query with Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedEntities = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Assuming ID is 1 for the example
                    .executeUpdate();

            System.out.println("Number of records updated: " + updatedEntities);

            // Commit transaction
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}

