import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.MessageDB;

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

    }

}
