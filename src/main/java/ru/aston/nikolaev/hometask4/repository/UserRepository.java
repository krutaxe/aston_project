package ru.aston.nikolaev.hometask4.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.aston.nikolaev.hometask2.util.HibernateConfig;
import ru.aston.nikolaev.hometask4.model.User;
import java.util.Optional;

public class UserRepository {
    SessionFactory sf = HibernateConfig.getSessionFactory();

    public Optional<User> add(User user) {
        Optional<User> rsl;
        try (Session session = sf.openSession()){
            session.save(user);
            rsl = Optional.of(user);
        } catch (Exception e) {
            rsl = Optional.empty();
        }
        return rsl;
    }

    public Optional<User> checkUser(String login, String pwd) {
        try (Session session = sf.openSession();){

            Query<User> query = session.createQuery(
                    "FROM User WHERE login = :fLogin AND password = :fPwd");
            query.setParameter("fLogin", login);
            query.setParameter("fPwd", pwd);
            return query.uniqueResultOptional();
        }
    }
}
