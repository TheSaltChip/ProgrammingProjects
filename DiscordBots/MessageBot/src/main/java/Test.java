import database.DAO.MessageDAO;
import database.objects.MessageDB;

import javax.ejb.EJB;
import java.util.List;

public class Test {
    @EJB
    private MessageDAO dao = new MessageDAO();

    public void main() {
        List<MessageDB> m = dao.getAllMessages();

        System.out.println(m);
    }
}
