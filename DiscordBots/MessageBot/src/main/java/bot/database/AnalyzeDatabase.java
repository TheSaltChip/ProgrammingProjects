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
        List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        //Compute the info about the letters
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

        //Compute the info about the words
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
                                }
                        )
                );

        if (INFO_DAO.get(USER.getId()) != null) {
            Info info = INFO_DAO.get(USER.getId());
            System.out.println(info);

            INFO_DAO.insert(USER.getId(), letters, words);

            info = INFO_DAO.get(USER.getId());
            System.out.println(info);

        } else {
            Info info = new Info(USER_DAO.get(USER.getId()), words, letters);
            USER_DAO.add(info);
            INFO_DAO.insert(info);
        }
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }
}
