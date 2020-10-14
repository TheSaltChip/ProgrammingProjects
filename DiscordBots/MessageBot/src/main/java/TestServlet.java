import database.DAO.MessageDAO;
import database.objects.MessageDB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TestServlet extends HttpServlet {
    @EJB
    private MessageDAO dao = new MessageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MessageDB> m = dao.getAllMessages();

        System.out.println(m);
    }
}


