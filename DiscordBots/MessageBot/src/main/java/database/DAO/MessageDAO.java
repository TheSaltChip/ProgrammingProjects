package database.DAO;

import database.objects.MessageDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class MessageDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotPU");

    /**
     * Returns all the message-objects in the schema
     *
     * @return An unordered list of message objects
     */
    public List<MessageDB> getAllMessages() {
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

    /**
     * Attempts to get all the messages sent by a given user
     * Returns an empty list if the given user has not written a message
     *
     * @param username      The actual name of the user
     * @param discriminator The 4 digit discord-tag starting with a #, example: #1234
     * @return List of all the messages sent by the given user
     */
    public List<MessageDB> getMessagesFromOneUser(String username, String discriminator) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m from MessageDB m where m.author = :username", MessageDB.class)
                    .setParameter("username", username + discriminator)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
        return messageDBList;
    }

    /**
     * Attempts to find a message with the given id
     *
     * @param id The id of the message you want
     * @return The message with the given id if it exists
     */
    public MessageDB getMessageById(String id) {
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


    /**
     * Supposedly this method makes it possible to search for messages
     * containing a substring
     *
     * @param searchTerm The string you want to search for
     * @return A list of all the messages that contains the searchTerm
     */
    public List<MessageDB> searchAfterMessageWithString(String searchTerm) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MessageDB> messageDBList = null;

        try {
            tx.begin();

            messageDBList = em.createQuery("select m from MessageDB m where locate(m.msg_content, :search) > 0 ", MessageDB.class)
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

    /**
     * Inserts a list of messages to the database
     * All the messages must have the author already in the database,
     * since the message entity is dependent on it
     *
     * @param messages The list of messages that are going to be added
     */
    public void insertMessages(List<MessageDB> messages) {
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

    public boolean checkIfMessageExists(String id) {
        return getMessageById(id) != null;
    }

}
