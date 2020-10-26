package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.letter.LetterBotDAO;
import database.dao.user.UserBotDAO;
import database.dao.word.WordBotDAO;
import database.objects.Info;
import database.objects.LetterAmount;
import database.objects.MessageDB;
import database.objects.WordAmount;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeDatabase {
    private final User USER;
    private final UserDAO USER_DAO = new UserBotDAO();
    private final InfoDAO INFO_DAO = new InfoBotDAO();
    private final LetterBotDAO LETTER_DAO = new LetterBotDAO();
    private final WordBotDAO WORD_DAO = new WordBotDAO();

    public AnalyzeDatabase(User user) {
        USER = user;
    }

    public void compute() {
        List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Info info = INFO_DAO.get(USER.getId());

        //Compute the info about the letters
        List<LetterAmount> letterAmounts = info == null ? new LinkedList<>() : info.getLetters();

        messages.forEach(m -> m.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> !(c == ' '))
                .forEach(c -> {
                    LetterAmount letterAmount = letterAmounts.stream()
                            .filter(l -> l.getLetter() == c)
                            .findFirst().orElse(null);

                    if (letterAmount == null) {
                        letterAmount = new LetterAmount(c, 1);
                        letterAmounts.add(letterAmount);
                    } else {
                        letterAmount.incTimes();
                    }

                })
        );

        //Compute the info about the words
        List<WordAmount> wordAmounts = info == null ? new LinkedList<>() : info.getWords();

        messages.stream()
                .map(s -> s.split(" "))
                .forEach(sA -> Arrays.stream(sA)
                        .filter(s -> !(s.matches("[,. ?!]") || s.length() > 100))
                        .map(s -> s.replaceAll("[^a-zA-ZøæåØÆÅ]", ""))
                        .forEach(s -> {
                                    WordAmount word = wordAmounts.stream()
                                            .filter(w -> w.getWord().equals(s))
                                            .findFirst().orElse(null);

                                    if (word == null) {
                                        word = new WordAmount(s, 1);
                                        wordAmounts.add(word);
                                    } else {
                                        word.incTimes();
                                    }
                                }
                        )
                );

        System.out.println(letterAmounts);
        System.out.println();
        System.out.println(wordAmounts);

        LETTER_DAO.insert(letterAmounts);
        WORD_DAO.insert(wordAmounts);

        if (info == null) {
            info = new Info(USER_DAO.get(USER.getId()), wordAmounts, letterAmounts);

            for (int i = 0, j = 0;;){
                if(i < wordAmounts.size()){
                    wordAmounts.get(i).setInfo(info);
                    i++;
                }
                if(j < letterAmounts.size()){
                    letterAmounts.get(j).setInfo(info);
                    j++;
                }

                if(j == letterAmounts.size() && i == wordAmounts.size()) break;
            }

            INFO_DAO.insert(info);
            USER_DAO.add(info);

        } else {
            INFO_DAO.insert(info, letterAmounts, wordAmounts);
        }
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }
}