package database.dao.letter;

import database.objects.Letter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class LetterBotDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    public void insert(Letter letter) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(letter);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }

    public void insert(List<Letter> letters) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            letters.forEach(em::persist);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }
}
