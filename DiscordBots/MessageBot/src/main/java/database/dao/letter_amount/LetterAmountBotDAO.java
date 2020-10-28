package database.dao.letter_amount;

import database.objects.LetterAmount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class LetterAmountBotDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DiscordBotLocalPU");

    public void insert(LetterAmount l) {
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

    public void update(LetterAmount la, Integer i) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            LetterAmount letterAmount = em.merge(la);
            letterAmount.setAmount(i);

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }
    }

    public LetterAmount get(String id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        LetterAmount l = null;
        try {
            tx.begin();

            l = em.createQuery("select l from LetterAmount l where l.info.id = :id", LetterAmount.class)
                    .setParameter("id", id)
                    .getSingleResult();

            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

        } finally {
            em.close();
        }

        return l;
    }
}
