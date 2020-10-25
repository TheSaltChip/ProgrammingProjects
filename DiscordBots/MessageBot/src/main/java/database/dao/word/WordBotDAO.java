package database.dao.word;

import database.objects.Word;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class WordBotDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    public void insert(Word word){
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(word);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }

    public void insert(List<Word> words) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            words.forEach(em::persist);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }
}
