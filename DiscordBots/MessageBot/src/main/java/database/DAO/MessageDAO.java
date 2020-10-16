package database.DAO;

import database.objects.MessageDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MessageDAO {
    @PersistenceContext(name = "DiscordBotPU")
    private EntityManagerFactory emf;
    @PersistenceContext(name = "DiscordBotPU")
    private EntityManager em = emf.createEntityManager();

    /**
     * Returns all the message-objects in the schema
     *
     * @return An unordered list of message objects
     */
    public List<MessageDB> getAllMessages() {
        return em.createQuery("select m from MessageDB m", MessageDB.class).getResultList();
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
        return em.createQuery("select m from MessageDB m where m.author = :username", MessageDB.class)
                .setParameter("username", username + discriminator)
                .getResultList();
    }

    /**
     * Attempts to find a message with the given id
     *
     * @param id The id of the message you want
     * @return The message with the given id if it exists
     */
    public MessageDB getMessageById(String id) {
        return em.find(MessageDB.class, id);
    }


    /**
     * Supposedly this method makes it possible to search for messages
     * containing a substring
     *
     * @param searchTerm The string you want to search for
     * @return A list of all the messages that contains the searchTerm
     */
    public List<MessageDB> searchAfterMessageWithString(String searchTerm) {
        return em.createQuery("select m from MessageDB m where locate(m.msg_content, :search) > 0 ", MessageDB.class)
                .setParameter("search", searchTerm)
                .getResultList();
    }

    /**
     * Inserts a list of messages to the database
     * All the messages must have the author already in the database,
     * since the message entity is dependent on it
     *
     * @param messages The list of messages that are going to be added
     */
    public void insertMessages(List<MessageDB> messages) {
        messages.forEach(m -> em.persist(m));
    }

    public boolean checkIfMessageExists(String id) {
        return getMessageById(id) != null;
    }

}
