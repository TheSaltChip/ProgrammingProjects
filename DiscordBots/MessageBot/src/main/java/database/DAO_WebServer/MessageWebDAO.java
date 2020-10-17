package database.DAO_WebServer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class MessageWebDAO {
    @EJB
    private EntityManager em;


}
