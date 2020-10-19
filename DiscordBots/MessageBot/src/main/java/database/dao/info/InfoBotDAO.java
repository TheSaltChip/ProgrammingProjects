package database.dao.info;

import database.objects.Info;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class InfoBotDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    public List<Info> get() {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Info> infoList = null;

        try {
            tx.begin();

            infoList = em.createQuery("select i from Info i", Info.class).getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return infoList;
    }

    public Info get(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Info info = null;

        try {
            tx.begin();

            info = em.createQuery("select i from Info i " +
                    "where i.user = (select u from UserDB u " +
                    "where u.id = :id)", Info.class)
                    .setParameter("id", user_id)
                    .getSingleResult();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return info;
    }

    public Map<Character, Integer> getLetters(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Map<Character, Integer> letters = new HashMap<>();

        try {
            tx.begin();

            List<Object[]> list = em.createNativeQuery("select letter, times from guild.letter where info_id = (select id from guild.info where user_id = '" + user_id + "')")
                    .getResultList();

            for (Object[] result :
                    list) {
                letters.put(result[0].toString().charAt(0), Integer.parseInt(result[1].toString()));
            }

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return letters;
    }

    public void insert(Map<Character, Integer> lettersInfo) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.persist(lettersInfo);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }
}
