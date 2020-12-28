package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("create table if not exists user (id mediumint primary key not null AUTO_INCREMENT, name CHAR(30) not null, lastName char(30) not null, age int not null)").executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("drop table if exists user").executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("delete where id=" + id);
        tx1.commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        tx1.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users = getAllUsers();
        for (User user : users) {
            session.delete(user);
        }
        tx1.commit();
        session.close();
    }
}
