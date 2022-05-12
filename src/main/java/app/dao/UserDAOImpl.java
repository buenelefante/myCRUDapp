package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO{
    public UserDAOImpl() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;
    }

    @Transactional
    @Override
    public void save(User user) {
        User managed = entityManager.merge(user);
        entityManager.persist(managed);
    }

    @Transactional
    @Override
    public void delete(User user) {
        User managed = entityManager.merge(user);
        entityManager.remove(managed);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }
}
