import database.dao.adt.InfoDAO;
import database.dao.amount.AmountBotDAO;
import database.dao.info.InfoBotDAO;
import database.dao.letter.LetterBotDAO;
import database.dao.letter_amount.LetterAmountBotDAO;
import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.Info;
import database.objects.MessageDB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static final InfoDAO INFO_DAO = new InfoBotDAO();
    private static final LetterAmountBotDAO LETTER_AMOUNT_DAO = new LetterAmountBotDAO();
    private static MessageBotDAO dao = new MessageBotDAO();
    private static UserBotDAO userBotDAO = new UserBotDAO();
    private static AmountBotDAO amountBotDAO = new AmountBotDAO();
    private static LetterBotDAO letterDAO = new LetterBotDAO();

    public static void main(String[] args) throws InterruptedException {
        List<String> messages = userBotDAO.get("159995722429628416").getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        Info info = INFO_DAO.get("159995722429628416");

        if (info == null) {
            info = new Info(userBotDAO.get("159995722429628416"));
            INFO_DAO.insert(info);
            info = INFO_DAO.get("159995722429628416");
        }

        Map<Character, Integer> letters = new HashMap<>();

        messages.stream()
                .map(m -> m.replaceAll("[^\\p{L}]+", ""))
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

        /*Map<String, Integer> words = new HashMap<>();

        messages.stream()
                .map(m -> m.split(" "))
                .forEach(sa ->
                        Arrays.stream(sa)
                                .filter(s -> !s.equals(" "))
                                .map()
                );*/

        System.out.println(letters);
    }

}
