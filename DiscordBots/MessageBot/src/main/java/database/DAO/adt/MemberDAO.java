package database.DAO.adt;

import database.objects.MemberDB;

import java.util.List;

public interface MemberDAO {
    /**
     * Returns all the members in the schema
     *
     * @return A list of all the members
     */
    List<MemberDB> get();

    /**
     * Attempts to get a member with the given id
     *
     * @param id The id that is to be search for
     * @return The member with the id if it exists
     */
    MemberDB get(String id);

    /**
     * Attempts to find all the members with the given username
     * And since multiple people can have the same username it returns
     * a list of the users with the username
     *
     * @param username The username you want to search for
     * @return A list of all the users with the given username
     */
    List<MemberDB> find(String username);

    /**
     * Attempts to find a member by their full discord name, which includes their username and discord tag
     *
     * @param username      The username of the member that is to be search for
     * @param discriminator The 4 digit discord tag starting with #, example #1234
     * @return A member with the given username and discriminator
     */
    MemberDB get(String username, String discriminator);

    /**
     * Inserts a list of members into the database
     *
     * @param members The list of members that are going to be added
     */
    void insert(List<MemberDB> members);

    /**
     * Inserts a member into the database
     *
     * @param member The member that is to be inserted to the database
     */
    void insert(MemberDB member);

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
