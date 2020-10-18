package bot.database;

import database.dao.user.UserBotDAO;
import database.dao.message.MessageBotDAO;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class AnalyzeDatabase {
    private final TextChannel TEXT_CHANNEL;
    private final User USER;
    MessageBotDAO messageBotDAO = new MessageBotDAO();
    UserBotDAO userBotDAO = new UserBotDAO();

    public AnalyzeDatabase(TextChannel text_channel, User user) {
        TEXT_CHANNEL = text_channel;
        USER = user;
    }

    public boolean exists(){
        return userBotDAO.exists(USER.getId());
    }

    public void getWords(){

    }
}
