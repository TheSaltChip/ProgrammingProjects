package database.dao.letter;

import database.objects.Letter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class LetterBotDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    public void insert(Letter l) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(l);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }

    public Letter get(Character c) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Letter l = null;
        try {
            tx.begin();

            l = em.find(Letter.class, c);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return l;
    }

    public List<Letter> get(){
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Letter> l = null;

        try {
            tx.begin();

            l = em.createQuery("select l from Letter l", Letter.class).getResultList();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return l;
    }

}
