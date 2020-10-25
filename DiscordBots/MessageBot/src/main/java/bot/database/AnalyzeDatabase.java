package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.MessageDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.message.MessageBotDAO;
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
    private final MessageDAO MESSAGE_DAO = new MessageBotDAO();

    public AnalyzeDatabase(User user) {
        USER = user;
    }

    // TODO -- Override the contains method in each list to conform with my need
    public void compute() {
        List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        //Compute the info about the letters
        List<Letter> letters = new LinkedList<>();

        messages.forEach(m -> m.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> {
                    if (!letters.contains(c)) {
                        letters.add(new Letter(c, 1));
                    } else {
                        letters.stream().filter(l -> l.getLetter() == c).forEach(l -> l.setTimes(l.getTimes() + 1));
                    }
                })
        );

        //Compute the info about the words
        List<Word> words = new LinkedList<>();

        messages.stream()
                .map(s -> s.split(" "))
                .forEach(sA -> Arrays.stream(sA)
                        .filter(s -> (s.matches("[^,. ?!]") || s.length() > 100))
                        .map(s -> s.replaceAll("[^a-zA-Z]", ""))
                        .forEach(s -> {
                                    if (!words.contains(s)) {
                                        words.add(new Word(s, 1));
                                    } else {
                                        words.stream()
                                                .filter(w -> w.getWord().equals(s))
                                                .forEach(w -> w.setTimes(w.getTimes() + 1));
                                    }
                                }
                        )
                );

        if (INFO_DAO.get().isEmpty()) {
            Info info = new Info(USER_DAO.get(USER.getId()), words, letters);

            System.out.println(info.getId() + " " + info.getUser() + " " + info.getLetters() + " " + info.getWords());
            System.out.println();
            INFO_DAO.insert(info);
            System.out.println("HELLO");
            System.out.println();
            USER_DAO.add(info);
            System.out.println("GoodBye");

        } else if (INFO_DAO.get(USER.getId()) != null) {
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