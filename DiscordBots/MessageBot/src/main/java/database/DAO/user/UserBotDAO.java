package database.DAO.user;

import database.DAO.adt.UserDAO;
import database.objects.UserDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class UserBotDAO implements UserDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    @Override
    public List<UserDB> get() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<UserDB> userDBS = null;

        try {
            tx.begin();

            userDBS = em.createQuery("select m from UserDB m", UserDB.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        return userDBS;
    }

    @Override
    public UserDB get(String id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        UserDB userDB = null;

        try {
            tx.begin();

            userDB = em.find(UserDB.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return userDB;
    }

    @Override
    public List<UserDB> find(String username) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<UserDB> userDBS = null;

        try {
            tx.begin();

            userDBS = em.createQuery("select m from UserDB m where m.username = :username", UserDB.class)
                    .setParameter("username", username)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return userDBS;
    }

    @Override
    public UserDB get(String username, String discriminator) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        UserDB userDB = null;

        try {
            tx.begin();

            userDB = em.createQuery("select m from UserDB m where m.username = :username and m.discriminator = :discriminator", UserDB.class)
                    .setParameter("username", username)
                    .setParameter("discriminator", discriminator)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return userDB;
    }

    /**
     * Inserts a list of members into the database
     *
     * @param members The list of members that are going to be added
     */
    public void insert(List<UserDB> members) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            members.forEach(em::persist);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

    }

    /**
     * Inserts a member into the database
     *
     * @param member The member that is to be inserted to the database
     */
    public void insert(UserDB member) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(member);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Checks if the user given by the id exists in the database
     *
     * @param id The id of the user
     * @return True if the user exists, false if not
     */
    public boolean exists(String id) {
        return get(id) != null;
    }

    /**
     * Checks if a user with the given username and discord tag exists in the database
     *
     * @param username      The username of the user
     * @param discriminator The discord tag of the user, example #1234
     * @return True if the user exists, false if not
     */
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }
}
