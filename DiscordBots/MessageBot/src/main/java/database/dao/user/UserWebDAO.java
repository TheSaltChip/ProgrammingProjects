package database.dao.user;

import database.dao.adt.UserDAO;
import database.objects.UserDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserWebDAO implements UserDAO {
    @PersistenceContext (name = "DiscordBotJTAPU")
    private EntityManager em;


    @Override
    public List<UserDB> get() {
        return em.createQuery("select m from UserDB m", UserDB.class)
                .getResultList();
    }

    @Override
    public UserDB get(String id) {
        return em.find(UserDB.class, id);
    }

    @Override
    public List<UserDB> find(String username) {
        return em.createQuery("select m from UserDB m " +
                "where m.username = :username", UserDB.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public UserDB get(String username, String discriminator) {
        return em.createQuery("select m from UserDB m " +
                "where m.username = :username " +
                "and m.discriminator = :discriminator", UserDB.class)
                .setParameter("username", username)
                .setParameter("discriminator", discriminator)
                .getSingleResult();
    }

    @Override
    public void insert(List<UserDB> members) {
        members.forEach(em::persist);
    }

    @Override
    public void insert(UserDB member) {
        em.persist(member);
    }

    @Override
    public boolean exists(String id) {
        return em.find(UserDB.class, id) != null;
    }

    @Override
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }
}
