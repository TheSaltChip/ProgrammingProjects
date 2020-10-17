package database.DAO_WebServer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberWebDAO {
    @PersistenceContext (name = "DiscordBotJTAPU")
    private EntityManager em;


}
