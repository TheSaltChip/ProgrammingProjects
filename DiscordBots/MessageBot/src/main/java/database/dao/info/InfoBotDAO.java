package database.dao.info;

import database.dao.adt.InfoDAO;
import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.WordAmount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("DuplicatedCode")
public class InfoBotDAO implements InfoDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    @Override
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

    @Override
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

    @Override
    @SuppressWarnings("unchecked")
    public Map<Character, Integer> getLetters(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Map<Character, Integer> letters = new TreeMap<>(
                Comparator.comparingInt(c -> c)
        );

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

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> getWords(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Map<String, Integer> words = new TreeMap<>(
                Comparator.comparing(c -> c)
        );

        try {
            tx.begin();

            List<Object[]> list = em.createNativeQuery("select word, times from guild.word where info_id = (select id from guild.info where user_id = '" + user_id + "')")
                    .getResultList();

            for (Object[] result :
                    list) {
                words.put(result[0].toString(), Integer.parseInt(result[1].toString()));
            }

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return words;
    }

    @Override
    public void insert(Info info) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(info);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Info info, List<LetterAmount> letterTimes, List<WordAmount> words) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            info.setLetters(letterTimes);
            info.setWords(words);

            em.merge(info);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }
}
