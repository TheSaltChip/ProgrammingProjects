package database.DAO.member;

import database.DAO.adt.MemberDAO;
import database.objects.MemberDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MemberWebDAO implements MemberDAO {
    @PersistenceContext (name = "DiscordBotJTAPU")
    private EntityManager em;


    @Override
    public List<MemberDB> get() {
        return em.createQuery("select m from MemberDB m", MemberDB.class)
                .getResultList();
    }

    @Override
    public MemberDB get(String id) {
        return em.find(MemberDB.class, id);
    }

    @Override
    public List<MemberDB> find(String username) {
        return em.createQuery("select m from MemberDB m " +
                "where m.username = :username", MemberDB.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public MemberDB get(String username, String discriminator) {
        return em.createQuery("select m from MemberDB m " +
                "where m.username = :username " +
                "and m.discriminator = :discriminator", MemberDB.class)
                .setParameter("username", username)
                .setParameter("discriminator", discriminator)
                .getSingleResult();
    }

    @Override
    public void insert(List<MemberDB> members) {
        members.forEach(em::persist);
    }

    @Override
    public void insert(MemberDB member) {
        em.persist(member);
    }

    @Override
    public boolean exists(String id) {
        return em.find(MemberDB.class, id) != null;
    }

    @Override
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }
}
