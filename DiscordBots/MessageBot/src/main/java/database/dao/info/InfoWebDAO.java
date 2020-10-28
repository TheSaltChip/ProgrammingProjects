package database.dao.info;

import database.dao.adt.InfoDAO;
import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.WordAmount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class InfoWebDAO implements InfoDAO {
    @PersistenceContext(name = "DiscordBotJTAPU")
    EntityManager em;

    @Override
    public List<Info> get() {
        return em.createQuery("select i from Info i", Info.class).getResultList();
    }

    @Override
    public Info get(String user_id) {
        return em.createQuery("select i from Info i " +
                "where i.user = (select u from UserDB u " +
                "where u.id = :id)", Info.class)
                .setParameter("id", user_id)
                .getSingleResult();
    }

    @Override
    public List<LetterAmount> getLetters(String user_id) {
        return em.createQuery("select la from LetterAmount la where la.info.id = :user_id", LetterAmount.class)
                .setParameter("user_id", user_id)
                .getResultList();

    }

    @Override
    public List<WordAmount> getWords(String user_id) {
        return em.createQuery("select wa from WordAmount wa where wa.info.id = :user_id", WordAmount.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    @Override
    public void insert(Info info) {
        em.persist(info);
    }

    @Override
    public void update(Info info, List<LetterAmount> letterTimes, List<WordAmount> words) {
        info.setWords(words);
        info.setLetters(letterTimes);
        em.merge(info);
    }

}
