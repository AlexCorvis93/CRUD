package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }


    @Override
    public List<User> users() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    @Override
    public User showUser(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        User user = showUser(id);
        entityManager.remove(user);
    }


    @Override
    public void update(int id, User update) {
        User user = showUser(id);
        user.setName(update.getName());
        user.setLastname(update.getLastname());
        user.setCountry(update.getCountry());
        entityManager.merge(user);

    }
}
