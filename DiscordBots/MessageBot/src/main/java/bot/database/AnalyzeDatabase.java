package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.Info;
import database.objects.Letter;
import database.objects.MessageDB;
import database.objects.Word;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeDatabase {
    private final User USER;
    private final UserDAO USER_DAO = new UserBotDAO();
    private final InfoDAO INFO_DAO = new InfoBotDAO();

    public AnalyzeDatabase(User user) {
        USER = user;
    }

    // TODO -- Override the contains method in each list to conform with my need
    public void compute() {
        List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Info info = INFO_DAO.get(USER.getId());

        //Compute the info about the letters
        List<Letter> letters = info == null ? new LinkedList<>() : info.getLetters();

        messages.forEach(m -> m.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> {
                    Letter letter = letters.stream()
                            .filter(l -> l.getLetter() == c)
                            .findFirst().orElse(null);

                    if (letter == null) {
                        letter = new Letter(c, 1);
                    } else {
                        letter.incTimes();
                        letters.remove(letter);
                    }
                    letters.add(letter);
                })
        );

        //Compute the info about the words
        List<Word> words = info == null ? new LinkedList<>() : info.getWords();

        messages.stream()
                .map(s -> s.split(" "))
                .forEach(sA -> Arrays.stream(sA)
                        .filter(s -> !(s.matches("[,. ?!]") || s.length() > 100))
                        .map(s -> s.replaceAll("[^a-zA-ZøæåØÆÅ]", ""))
                        .forEach(s -> {
                                    Word word = words.stream()
                                            .filter(w -> w.getWord().equals(s))
                                            .findFirst().orElse(null);

                                    if (word == null) {
                                        word = new Word(s, 1);
                                    } else {
                                        word.incTimes();
                                        words.remove(word);
                                    }

                                    words.add(word);
                                }
                        )
                );

        if (info == null) {
            info = new Info(USER_DAO.get(USER.getId()), words, letters);

            INFO_DAO.insert(info);
            USER_DAO.add(info);

        } else {
            INFO_DAO.insert(USER.getId(), letters, words);


        }
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }
}