import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.Letter;
import database.objects.MessageDB;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static MessageBotDAO dao = new MessageBotDAO();
    private static UserBotDAO userBotDAO = new UserBotDAO();

    public static void main(String[] args) {
        List<String> messages = userBotDAO.get("159995722429628416").getMessages()
                .stream()
                .map(MessageDB::getMsg_content)
                .collect(Collectors.toList());

        System.out.println("Messages:\n" + messages + "\n\n");

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

        System.out.println("Letters\n" +letters + "\n\n");
    }

}
