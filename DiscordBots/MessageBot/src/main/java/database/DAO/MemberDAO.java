package database.DAO;

import database.objects.MemberDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class MemberDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    /**
     * Returns all the members in the schema
     *
     * @return A list of all the members
     */
    public List<MemberDB> getAllMembers() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MemberDB> memberDBS = null;

        try {
            tx.begin();

            memberDBS = em.createQuery("select m from MemberDB m", MemberDB.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        return memberDBS;
    }

    /**
     * Attempts to find a member with the given id
     *
     * @param id The id that is to be search for
     * @return The member with the id if it exists
     */
    public MemberDB findMemberById(String id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        MemberDB memberDB = null;

        try {
            tx.begin();

            memberDB = em.find(MemberDB.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return memberDB;
    }

    /**
     * Attempts to find all the members with the given username
     * And since multiple people can have the same username it returns
     * a list of the users with the username
     *
     * @param username The username you want to search for
     * @return A list of all the users with the given username
     */
    public List<MemberDB> findMemberByUsername(String username) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<MemberDB> memberDBS = null;

        try {
            tx.begin();

            memberDBS = em.createQuery("select m from MemberDB m where m.username = :username", MemberDB.class)
                    .setParameter("username", username)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return memberDBS;
    }

    /**
     * Attempts to find a member by their full discord name, which includes their username and discord tag
     *
     * @param username      The username of the member that is to be search for
     * @param discriminator The 4 digit discord tag starting with #, example #1234
     * @return A member with the given username and discriminator
     */
    public MemberDB getMemberWithUsernameDisc(String username, String discriminator) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        MemberDB memberDB = null;

        try {
            tx.begin();

            memberDB = em.createQuery("select m from MemberDB m where m.username = :username and m.discriminator = :discriminator", MemberDB.class)
                    .setParameter("username", username)
                    .setParameter("discriminator", discriminator)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return memberDB;
    }

    /**
     * Inserts a list of members into the database
     *
     * @param members The list of members that are going to be added
     */
    public void insertMembers(List<MemberDB> members) {
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

    public void insertMember(MemberDB member) {
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
    public boolean checkIfUserExistsId(String id) {
        return findMemberById(id) != null;
    }

    /**
     * Checks if a user with the given username and discord tag exists in the database
     *
     * @param username      The username of the user
     * @param discriminator The discord tag of the user, example #1234
     * @return True if the user exists, false if not
     */
    public boolean checkIfUserExistsUsernameDisc(String username, String discriminator) {
        return getMemberWithUsernameDisc(username, discriminator) != null;
    }
}
