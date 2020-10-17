package database.DAO.adt;

import database.objects.MessageDB;

import java.util.List;

public interface MessageDAO {
    /**
     * Returns all the message-objects in the schema
     *
     * @return An unordered list of message objects
     */
    List<MessageDB> get();

    /**
     * Attempts to get all the messages sent by a given user
     * Returns an empty list if the given user has not written a message
     *
     * @param username      The actual name of the user
     * @param discriminator The 4 digit discord-tag starting with a #, example: #1234
     * @return List of all the messages sent by the given user
     */
    List<MessageDB> get(String username, String discriminator);

    /**
     * Attempts to find a message with the given id
     *
     * @param id The id of the message you want
     * @return The message with the given id if it exists
     */
    MessageDB get(String id);

    /**
     * Supposedly this method makes it possible to search for messages
     * containing a substring
     *
     * @param searchTerm The string you want to search for
     * @return A list of all the messages that contains the searchTerm
     */
    List<MessageDB> search(String searchTerm);

    /**
     * Inserts a list of messages to the database
     * All the messages must have the author already in the database,
     * since the message entity is dependent on it
     *
     * @param messages The list of messages that are going to be added
     */
    void insert(List<MessageDB> messages);

    /**
     * Checks if the message already exists in the database
     * by checking if the id of the message corresponds to one
     * of the messages already in the database
     *
     * @param id The ID of the message you want to check
     * @return True if the message id exists in the database, false if not
     */
    boolean exists(String id);

}
