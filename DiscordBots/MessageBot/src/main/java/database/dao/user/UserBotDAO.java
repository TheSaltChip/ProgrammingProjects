package database.dao.user;

import database.dao.adt.UserDAO;
import database.objects.Info;
import database.objects.MessageDB;
import database.objects.UserDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserDB> get(List<MessageDB> messages) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<UserDB> users = null;

        List<String> msgIDs = messages.stream()
                .map(MessageDB::getAuthor)
                .map(UserDB::getId)
                .distinct()
                .collect(Collectors.toList());

        try {
            tx.begin();

            users = em.createQuery("select u from UserDB u where u.id in :msgIDs", UserDB.class)
                    .setParameter("msgIDs", msgIDs)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

        return users;
    }

    @Override
    public void insert(List<UserDB> users) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            users.forEach(em::persist);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

    }

    @Override
    public void insert(UserDB user) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(user);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exists(String id) {
        return get(id) != null;
    }

    @Override
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }

    @Override
    public void add(Info info) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            UserDB user = this.get(info.getUser().getId());

            UserDB newUser = em.merge(user);
            newUser.setInfo(info);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void update(List<MessageDB> dbList) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<UserDB> users = this.get(dbList);

            for (UserDB u :
                    users) {
                List<MessageDB> msg = dbList.stream()
                        .filter(m -> m.getAuthor().getId().equals(u.getId()))
                        .collect(Collectors.toList());
                u.updateMessages(msg);
                em.merge(u);
            }

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}
