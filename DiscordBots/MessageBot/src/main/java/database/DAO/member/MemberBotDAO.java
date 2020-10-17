package database.DAO.member;

import database.DAO.adt.MemberDAO;
import database.objects.MemberDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class MemberBotDAO implements MemberDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    @Override
    public List<MemberDB> get() {
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

    @Override
    public MemberDB get(String id) {
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

    @Override
    public List<MemberDB> find(String username) {
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

    @Override
    public MemberDB get(String username, String discriminator) {
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
    public void insert(List<MemberDB> members) {
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

    /**
     * Inserts a member into the database
     *
     * @param member The member that is to be inserted to the database
     */
    public void insert(MemberDB member) {
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
    public boolean exists(String id) {
        return get(id) != null;
    }

    /**
     * Checks if a user with the given username and discord tag exists in the database
     *
     * @param username      The username of the user
     * @param discriminator The discord tag of the user, example #1234
     * @return True if the user exists, false if not
     */
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }
}
