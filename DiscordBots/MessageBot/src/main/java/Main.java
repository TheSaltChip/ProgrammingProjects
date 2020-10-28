import database.dao.adt.InfoDAO;
import database.dao.info.InfoBotDAO;
import database.dao.letter.LetterBotDAO;
import database.dao.letter_amount.LetterAmountBotDAO;
import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.Info;
import database.objects.Letter;
import database.objects.LetterAmount;
import database.objects.MessageDB;

import java.util.*;
import java.util.stream.Collectors;

// TODO Create a method in userDAO that retrieves the date of the latest message in the database
// TODO Change methods in infoDAO that ensures the use of the latest date of the latest message
// TODO Could in theory link info to message, where the latest message references the message object

public class Main {
    private static final InfoDAO INFO_DAO = new InfoBotDAO();
    private static final LetterAmountBotDAO LETTER_AMOUNT_DAO = new LetterAmountBotDAO();
    private static final MessageBotDAO dao = new MessageBotDAO();
    private static final UserBotDAO userBotDAO = new UserBotDAO();
    private static final LetterBotDAO letterDAO = new LetterBotDAO();

    public static void main(String[] args) {
        List<String> messages = userBotDAO.get("159995722429628416").getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Map<Character, Integer> letters = new HashMap<>();

        //GATHERING DATA
        messages.stream()
                .map(m -> m.replaceAll(" ", ""))
                .reduce("", (a, b) -> a + b)
                .chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> {
                            if (!letters.containsKey(c)) {
                                letters.put(c, null);
                            }

                            letters.compute(c, (le, i) -> i == null ? 1 : i + 1);
                        }
                );

        Map<String, Integer> words = new HashMap<>();

        messages.stream()
                .filter(m -> m.length() <= 100)
                .map(m -> m.split(" "))
                .forEach(sa ->
                        Arrays.stream(sa)
                                .map(s -> s.replaceAll(" ", ""))
                                .forEach(w -> {
                                    if (!words.containsKey(w)) {
                                        words.put(w, null);
                                    }

                                    words.compute(w, (wo, i) -> i == null ? 1 : i + 1);
                                })
                );

        System.out.println(letters);
        System.out.println(words);


        // STORING DATA
        Info info = INFO_DAO.get("159995722429628416");

        if (info == null) {
            info = new Info(userBotDAO.get("159995722429628416"));
            INFO_DAO.insert(info);
            info = INFO_DAO.get("159995722429628416");
        }

        List<LetterAmount> letterAmounts = info.getLetters();
        letterAmounts = letterAmounts == null ? new LinkedList<>() : info.getLetters();

        if (letterAmounts.size() > 0) {
            for (LetterAmount la :
                    letterAmounts) {
                if (letters.containsKey(la.getLetter().getLetter())) {
                    LETTER_AMOUNT_DAO.update(la, la.getAmount());
                }
            }
        }


        Info finalInfo = info;
        letters.forEach((c, i) -> {
            //Check if there exists a letter entity, create a new one if there is none
            Letter l = letterDAO.get(c);
            if (l == null) {
                l = new Letter(c);
                letterDAO.insert(l);
            }

            letterAmounts.add(new LetterAmount(finalInfo, l, i));
        });
    }

}
