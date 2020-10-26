package database.dao.info;

import database.dao.adt.InfoDAO;
import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.WordAmount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @SuppressWarnings("unchecked")
    @Override
    public Map<Character, Integer> getLetters(String user_id) {
        Map<Character, Integer> letters = new TreeMap<>(
                Comparator.comparingInt(c -> c)
        );

        List<Object[]> list = em.createNativeQuery("select letter, amount from guild.letter_amount where info_id = '" + user_id + "'")
                .getResultList();

        for (Object[] result :
                list) {
            letters.put(result[0].toString().charAt(0), Integer.parseInt(result[1].toString()));
        }
        return letters;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Integer> getWords(String user_id) {
        Map<String, Integer> words = new TreeMap<>(
                Comparator.comparing(c -> c)
        );

        List<Object[]> list = em.createNativeQuery("select word, amount from guild.word_amount where info_id = '" + user_id + "'")
                .getResultList();

        for (Object[] result :
                list) {
            words.put(result[0].toString(), Integer.parseInt(result[1].toString()));
        }

        return words;
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
