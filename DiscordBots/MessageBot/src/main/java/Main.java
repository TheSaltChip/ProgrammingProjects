import database.DAO.MessageDAO;
import database.objects.MessageDB;

import java.util.List;

public class Main {
    private static MessageDAO dao = new MessageDAO();

    public static void main(String[] args) {
        List<MessageDB> m = dao.getAllMessages();

        System.out.println(m);
    }

}
