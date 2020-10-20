package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.MessageDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.Info;
import database.objects.MessageDB;
import net.dv8tion.jda.api.entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeDatabase {
    private final User USER;
    private final UserDAO USER_DAO = new UserBotDAO();
    private final InfoDAO INFO_DAO = new InfoBotDAO();
    private final MessageDAO MESSAGE_DAO = new MessageBotDAO();

    public AnalyzeDatabase(User user) {
        USER = user;
    }

    public void compute() {
        List<String> messages = MESSAGE_DAO.get(USER.getId())
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Map<Character, Integer> letters = this.computeLetters(messages);
        Map<String, Integer> words = this.computeWords(messages);

        if (INFO_DAO.get(USER.getId()) != null) {
            Info info = INFO_DAO.get(USER.getId());
            System.out.println(info);

            INFO_DAO.insert(USER.getId(), letters, words);

            info = INFO_DAO.get(USER.getId());
            System.out.println(info);
        } else {
            System.out.println("Right after else");
            Info info = new Info(USER_DAO.get(USER.getId()), words, letters);
            System.out.println("After creating");
            USER_DAO.add(info);
            System.out.println("After adding");
            INFO_DAO.insert(info);
            System.out.println("After inserting");
        }
    }

    public Map<Character, Integer> computeLetters(List<String> messages) {
        Map<Character, Integer> letters = new TreeMap<>(
                Comparator.comparingInt(c -> c)
        );

        messages.forEach(m -> m.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> {
                    if (!letters.containsKey(c)) {
                        letters.put(c, 1);
                    } else {
                        letters.compute(c, (k, i) -> (i == null) ? 1 : i + 1);
                    }
                })
        );

        return letters;

    }

    public Map<String, Integer> computeWords(List<String> messages) {
        Map<String, Integer> words = new TreeMap<>(
                Comparator.comparing(c -> c)
        );

        messages.stream()
                .map(s -> s.split(" "))
                .forEach(sA -> Arrays.stream(sA)
                        .filter(s -> !(s.matches("[,. ?!]") || s.length() > 100))
                        .forEach(s -> {
                            if (!words.containsKey(s)) {
                                words.put(s, 1);
                            } else {
                                words.compute(s, (k, i) -> (i == null) ? 1 : i + 1);
                            }
                        }));

        return words;
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }

    public Map<Character, Integer> getLetters() {
        return INFO_DAO.getLetters(USER.getId());
    }
}
