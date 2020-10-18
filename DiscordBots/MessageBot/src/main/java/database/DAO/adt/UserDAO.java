package database.DAO.adt;

import database.objects.UserDB;

import java.util.List;

public interface UserDAO {
    /**
     * Returns all the users in the schema
     *
     * @return A list of all the users
     */
    List<UserDB> get();

    /**
     * Attempts to get a user with the given id
     *
     * @param id The id that is to be search for
     * @return The user with the id if it exists
     */
    UserDB get(String id);

    /**
     * Attempts to find all the users with the given username
     * And since multiple people can have the same username it returns
     * a list of the users with the username
     *
     * @param username The username you want to search for
     * @return A list of all the users with the given username
     */
    List<UserDB> find(String username);

    /**
     * Attempts to find a user by their full discord name, which includes their username and discord tag
     *
     * @param username      The username of the user that is to be search for
     * @param discriminator The 4 digit discord tag starting with #, example #1234
     * @return A user with the given username and discriminator
     */
    UserDB get(String username, String discriminator);

    /**
     * Inserts a list of users into the database
     *
     * @param users The list of users that are going to be added
     */
    void insert(List<UserDB> users);

    /**
     * Inserts a user into the database
     *
     * @param user The user that is to be inserted to the database
     */
    void insert(UserDB user);

    /**
     * Checks if the user given by the id exists in the database
     *
     * @param id The id of the user
     * @return True if the user exists, false if not
     */
    boolean exists(String id);

    /**
     * Checks if a user with the given username and discord tag exists in the database
     *
     * @param username      The username of the user
     * @param discriminator The discord tag of the user, example #1234
     * @return True if the user exists, false if not
     */
    boolean exists(String username, String discriminator);
}
