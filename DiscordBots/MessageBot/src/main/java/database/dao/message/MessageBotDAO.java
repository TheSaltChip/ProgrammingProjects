package database.dao.message;

import database.dao.adt.MessageDAO;
import database.objects.Info;
import database.objects.MessageDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class MessageBotDAO implements MessageDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    @Override
    public List<MessageDB> get() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m from MessageDB m", MessageDB.class).getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return messageDBList;
    }

    @Override
    public List<MessageDB> get(String username, String discriminator) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m " +
                            "from MessageDB m " +
                            "where m.author = " +
                            "   (select mb from UserDB mb " +
                            "   where mb.username = :username " +
                            "   and mb.discriminator = :discriminator)",
                    MessageDB.class)
                    .setParameter("username", username)
                    .setParameter("discriminator", discriminator)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
        return messageDBList;
    }

    @Override
    public List<MessageDB> get(String id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m " +
                            "from MessageDB m " +
                            "where m.author = " +
                            "   (select mb from UserDB mb " +
                            "   where mb.id = :id)",
                    MessageDB.class)
                    .setParameter("id", id)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
        return messageDBList;
    }

    @Override
    public MessageDB find(String id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        MessageDB messageDB = null;

        try {
            tx.begin();

            messageDB = em.find(MessageDB.class, id);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return messageDB;
    }

    @Override
    public List<MessageDB> search(String searchTerm) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m from MessageDB m where locate(m.msgContent, :search) > 0 ", MessageDB.class)
                    .setParameter("search", searchTerm)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
        return messageDBList;
    }

    @Override
    public void insert(List<MessageDB> messages) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            messages.forEach(em::persist);

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
        return find(id) != null;
    }

    public MessageDB mostRecent() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        MessageDB messageDB = null;

        try {
            tx.begin();

            messageDB = em.createQuery("select m from MessageDB m where m.dateCreated = (select max(m.dateCreated) from MessageDB m)", MessageDB.class).getSingleResult();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

        return messageDB;
    }
}
