import database.DAO_Local.MessageBotDAO;
import database.objects.MessageDB;

import java.util.List;

public class Main {
    private static MessageBotDAO dao = new MessageBotDAO();

    public static void main(String[] args) {
        List<MessageDB> m = dao.getAllMessages();

        System.out.println(m);
    }

}
