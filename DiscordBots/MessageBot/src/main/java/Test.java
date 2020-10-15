import database.DAO.MessageDAO;
import database.objects.MessageDB;

import java.util.List;

public class Test {
    private MessageDAO dao;

    public void main() {
        List<MessageDB> m = dao.getAllMessages();

        System.out.println(m);
    }
}
