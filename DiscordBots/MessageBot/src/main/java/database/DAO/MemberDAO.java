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
}
