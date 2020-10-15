package database.DAO;

import database.objects.MemberDB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberDAO {
    @PersistenceContext(name = "DiscordBotPU")
    private EntityManager em;

    /**
     * Returns all the members in the schema
     *
     * @return A list of all the members
     */
    public List<MemberDB> getAllMembers(){
        return em.createQuery("select m from MemberDB m", MemberDB.class)
                .getResultList();
    }

    /**
     * Attempts to find a member with the given id
     *
     * @param id The id that is to be search for
     * @return The member with the id if it exists
     */
    public MemberDB findMemberById(int id){
        return em.find(MemberDB.class, id);
    }

    /**
     * Attempts to find all the members with the given username
     * And since multiple people can have the same username it returns
     * a list of the users with the username
     *
     * @param username The username you want to search for
     * @return A list of all the users with the given username
     */
    public List<MemberDB> findMemberByUsername(String username){
        return em.createQuery("select m from MemberDB m where m.username = :username", MemberDB.class)
                .setParameter("username", username)
                .getResultList();
    }

    /**
     * Attempts to find a member by their full discord name, which includes their username and discord tag
     *
     * @param username The username of the member that is to be search for
     * @param discriminator The 4 digit discord tag starting with #, example #1234
     * @return A member with the given username and discriminator
     */
    public MemberDB getMemberWithUsernameDisc(String username, String discriminator){
        return em.createQuery("select m from MemberDB m where m.username = :username and m.discriminator = :discriminator", MemberDB.class)
                .setParameter("username", username)
                .setParameter("discriminator", discriminator)
                .getSingleResult();
    }

    /**
     * Inserts a list of members into the database
     *
     * @param members The list of members that are going to be added
     */
    public void insertMembers(List<MemberDB> members){
        members.forEach(m -> em.persist(m));
    }


    /**
     * Checks if the user given by the id exists in the database
     *
     * @param id The id of the user
     * @return True if the user exists, false if not
     */
    public boolean checkIfUserExistsId(int id){
        MemberDB m = em.find(MemberDB.class, id);
        return m != null;
    }

    /**
     * Checks if a user with the given username and discord tag exists in the database
     *
     * @param username The username of the user
     * @param discriminator The discord tag of the user, example #1234
     * @return True if the user exists, false if not
     */
    public boolean checkIfUserExistsUsernameDisc(String username, String discriminator){
        MemberDB m = em.createQuery("select m from MemberDB m where m.discriminator = :discriminator and m.username = :username", MemberDB.class)
                .setParameter("discriminator", discriminator)
                .setParameter("username", username).getSingleResult();

        return m != null;
    }
}
