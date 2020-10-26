package bot.database;

import database.dao.adt.InfoDAO;
import database.dao.adt.UserDAO;
import database.dao.info.InfoBotDAO;
import database.dao.letter_amount.LetterAmountBotDAO;
import database.dao.user.UserBotDAO;
import database.dao.word_amount.WordAmountBotDAO;
import net.dv8tion.jda.api.entities.User;

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
        /*List<String> messages = USER_DAO.get(USER.getId()).getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Info info = INFO_DAO.get(USER.getId());

        if (info == null) {
            info = new Info(USER_DAO.get(USER.getId()));
            INFO_DAO.insert(info);
        }
        //LETTERS
        List<LetterAmount> letterAmounts = info.getLetters() == null ? new LinkedList<>() : info.getLetters();

        Character[] arr = (Character[]) messages.stream().map(s -> s.replaceAll(" ", "")).map(String::toCharArray).toArray();

        for (Character c :
                arr) {

            LetterAmount letterAmount = letterAmounts.stream()
                    .filter(l -> l.getLetter().equals(c))
                    .findFirst().orElse(null);

            if (letterAmount == null) {
                Letter l = new Letter(c);
                Amount a = new Amount(1);

                letterAmount = new LetterAmount(info, l, a);
                LETTER_DAO.insert(letterAmount);
            } else {
                Amount a = new Amount(letterAmount.getAmount().getAmount() + 1);
                LETTER_DAO.update(letterAmount, a);
            }
        }

        INFO_DAO.update(info, letterAmounts, null);

*/
    }

    public boolean exists() {
        return USER_DAO.exists(USER.getId());
    }
}