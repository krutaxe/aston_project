package ru.aston.nikolaev.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.aston.nikolaev.hometask2.util.HibernateConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoRepository {
    private static final SessionFactory sf = HibernateConfig.getSessionFactory();

    public static void addAuto() {
        Session session = sf.openSession();
        session.beginTransaction();
        for (int i = 0; i < 100_000; i++) {
            session.save(new Auto("Type" + i, "Color" + i, new Random().nextInt(10000)));
        }
        session.getTransaction().commit();
    }

    public static List<Auto> getAuto() {
        Session session = sf.openSession();

        Query<Auto> query = session.createQuery("FROM Auto WHERE number = 0");
        return query.list();
    }


    public static void main(String[] args) {
        List<Auto> autoList = new ArrayList<>();

        try (sf) {
            Session session = sf.getCurrentSession();
            session.beginTransaction();

//            for (int i = 0; i < 100; i++) {
//                autoList.add(new Auto("Type" + i, "Color" + i, i + 1000));
//            }
//
//            Driver driver = new Driver("Nikolaev", 25);
//            driver.setAutos(autoList);
//            session.persist(driver);

            Driver driver = session.get(Driver.class, 22);

            System.out.println(driver);

            session.getTransaction().commit();
            session.close();
            System.out.println("DONE");

        }

    }
}
