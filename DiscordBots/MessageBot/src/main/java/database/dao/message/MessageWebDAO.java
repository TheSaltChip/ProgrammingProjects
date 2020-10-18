package database.dao.message;

import database.dao.adt.MessageDAO;
import database.objects.MessageDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MessageWebDAO implements MessageDAO {
    @PersistenceContext(name = "DiscordBotJTAPU")
    private EntityManager em;

    @Override
    public List<MessageDB> get() {
        return em.createQuery("select m from MessageDB m", MessageDB.class)
                .getResultList();
    }

    @Override
    public List<MessageDB> get(String username, String discriminator) {
        return em.createQuery("select m " +
                        "from MessageDB m " +
                        "where m.author = " +
                        "   (select mb from UserDB mb " +
                        "   where mb.username = :username " +
                        "   and mb.discriminator = :discriminator)",
                MessageDB.class)
                .setParameter("username", username)
                .setParameter("discriminator", discriminator)
                .getResultList();
    }

    @Override
    public MessageDB get(String id) {
        return em.find(MessageDB.class, id);
    }

    @Override
    public List<MessageDB> search(String searchTerm) {
        return em.createQuery("select m from MessageDB m " +
                "where locate(m.msg_content, :search) > 0", MessageDB.class)
                .setParameter("search", searchTerm)
                .getResultList();
    }

    @Override
    public void insert(List<MessageDB> messages) {
        messages.forEach(em::persist);
    }

    @Override
    public boolean exists(String id) {
        return get(id) != null;
    }
}
