package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.letter_amount.LetterAmountBotDAO;
import database.dao.user.UserBotDAO;
import database.dao.word_amount.WordAmountBotDAO;
import database.objects.*;
import net.dv8tion.jda.api.entities.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzeDatabase {
    private final User USER;
    private final UserDAO USER_DAO = new UserBotDAO();
    private final InfoDAO INFO_DAO = new InfoBotDAO();
    private final LetterAmountBotDAO LETTER_DAO = new LetterAmountBotDAO();
    private final WordAmountBotDAO WORD_DAO = new WordAmountBotDAO();

    public AnalyzeDatabase(User user) {
        USER = user;
    }

    public void compute() {
      /* List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Info info = INFO_DAO.get("159995722429628416");

        if (info == null) {
            info = new Info(USER_DAO.get("159995722429628416"));
            INFO_DAO.insert(info);
            info = INFO_DAO.get("159995722429628416");
        }

        //LETTERS
        List<LetterAmount> letterAmounts = info.getLetters() == null ? new LinkedList<>() : info.getLetters();

        List<Character> chars = messages.stream()
                .map(s -> s.replaceAll(" ", ""))
                .reduce("", (a, b) -> a + b)
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        for (Character c :
                chars) {

            LetterAmount letterAmount = letterAmounts.stream()
                    .filter(l -> l != null && l.getLetter() != null)
                    .filter(l -> l.getLetter().getLetter() == c)
                    .findFirst().orElse(null);

            if (letterAmount == null) {
                System.out.println("NULL");

                Letter l = letterDAO.get(c);

                if (l == null) {
                    l = new Letter(c);
                    letterDAO.insert(l);
                }

                Amount a = amountBotDAO.get(1);
                System.out.println("A: " + a + "\nLetter: " + l);

                if (a == null) {
                    a = new Amount(1);
                    amountBotDAO.insert(a);
                }

                letterAmount = new LetterAmount(info, l, a);

                LETTER_AMOUNT_DAO.insert(letterAmount);

                letterAmounts.add(letterAmount);

            } else {
                System.out.println("NOT NULL");

                int newA = letterAmount.getAmount().getAmount() + 1;
                Amount a = amountBotDAO.get(newA);

                if(a == null){
                    a = new Amount(newA);
                    amountBotDAO.insert(a);
                }

                LETTER_AMOUNT_DAO.update(letterAmount, a);
            }
        }

        INFO_DAO.update(info, letterAmounts, null);

        System.out.println("Messages:\n" + messages + "\n\n");
*/
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }
}