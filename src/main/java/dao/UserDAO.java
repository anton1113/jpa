package dao;

import model.User;

import javax.persistence.*;

/**
 *
 * @author arasshchektaiev
 */
public class UserDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("users");
    private EntityManager em = factory.createEntityManager();

    public User getUser(String username, String password) {

        try {
            User user = (User) em.createQuery("select u from User u where u.username=:username and u.password=:password")
                    .setParameter("username", username)
                    .setParameter("password", password
                    ).getSingleResult();

            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean insertUser(User user) {
        try {
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        try {
            em.remove(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
