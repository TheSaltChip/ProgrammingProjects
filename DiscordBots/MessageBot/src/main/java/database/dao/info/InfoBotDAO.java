package database.dao.info;

import database.dao.adt.InfoDAO;
import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.WordAmount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
    public List<LetterAmount> getLetters(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<LetterAmount> letters = null;

        try {
            tx.begin();

            letters = em.createQuery("select la from LetterAmount la where la.info.id = :user_id", LetterAmount.class)
                    .setParameter("user_id", user_id)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return letters;
    }

    @Override
    public List<WordAmount> getWords(String user_id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<WordAmount> words = null;

        try {
            tx.begin();

            words = em.createQuery("select wa from WordAmount wa where wa.info.id = :user_id", WordAmount.class)
                    .setParameter("user_id", user_id)
                    .getResultList();

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
    public void update(Info info, List<LetterAmount> letterTimes, List<WordAmount> words) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Info newInfo = em.merge(info);

            newInfo.setLetters(letterTimes);
            newInfo.setWords(words);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }
}
