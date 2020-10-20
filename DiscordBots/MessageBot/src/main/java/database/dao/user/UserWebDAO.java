package database.dao.user;

import database.dao.adt.UserDAO;
import database.objects.Info;
import database.objects.MessageDB;
import database.objects.UserDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserWebDAO implements UserDAO {
    @PersistenceContext(name = "DiscordBotJTAPU")
    private EntityManager em;


    @Override
    public List<UserDB> get() {
        return em.createQuery("select m from UserDB m", UserDB.class)
                .getResultList();
    }

    @Override
    public UserDB get(String id) {
        return em.find(UserDB.class, id);
    }

    @Override
    public List<UserDB> find(String username) {
        return em.createQuery("select m from UserDB m " +
                "where m.username = :username", UserDB.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public UserDB get(String username, String discriminator) {
        return em.createQuery("select m from UserDB m " +
                "where m.username = :username " +
                "and m.discriminator = :discriminator", UserDB.class)
                .setParameter("username", username)
                .setParameter("discriminator", discriminator)
                .getSingleResult();
    }

    @Override
    public List<UserDB> get(List<MessageDB> messages) {
        return null;
    }

    @Override
    public void insert(List<UserDB> members) {
        members.forEach(em::persist);
    }

    @Override
    public void insert(UserDB member) {
        em.persist(member);
    }

    @Override
    public boolean exists(String id) {
        return em.find(UserDB.class, id) != null;
    }

    @Override
    public boolean exists(String username, String discriminator) {
        return get(username, discriminator) != null;
    }

    @Override
    public void add(Info info) {
        UserDB user = info.getUser();
        user.setInfo(info);
        em.merge(user);
    }

    @Override
    public void update(List<MessageDB> messageDBList) {
        
        List<UserDB> users = this.get(messageDBList);

        for (UserDB u :
                users) {
            List<MessageDB> msg = messageDBList.stream()
                    .filter(m -> m.getAuthor().getId().equals(u.getId()))
                    .collect(Collectors.toList());
            u.updateMessages(msg);
            em.merge(u);
        }
    }
}
