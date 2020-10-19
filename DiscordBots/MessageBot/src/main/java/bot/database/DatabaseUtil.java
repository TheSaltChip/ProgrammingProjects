package bot.database;

import database.dao.adt.UserDAO;
import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.MessageDB;
import database.objects.UserDB;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseUtil {
    private final MessageBotDAO messageBotDAO = new MessageBotDAO();
    private final UserBotDAO userBotDAO = new UserBotDAO();
    private boolean hasRun = false;
    private String lastMessage = null;

    public void setup(TextChannel channel) {
        //Gets all the users from the channel
        List<UserDB> userDBS = channel.getMembers().stream()
                .map(Member::getUser)
                .map(user -> new UserDB(user.getId(), user.getName(), "#" + user.getDiscriminator()))
                .collect(Collectors.toList());

        userBotDAO.insert(userDBS);

        //Messages
        MessageHistory messageHistory = channel.getHistory();
        List<Message> messages = null;

        while (messages == null || !messages.isEmpty()) {
            messages = messageHistory.retrievePast(100).complete();

            if(lastMessage == null){
                lastMessage = messages.get(0).getId();
            }

            List<MessageDB> dbList = messages.stream()
                    .map(m -> new MessageDB(m.getId(),
                            userBotDAO.get(m.getAuthor().getId()),
                            m.getContentStripped()))
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
        }

        hasRun = true;
    }

    public boolean hasRun(){
        return hasRun;
    }

    public void update(TextChannel channel) {
        //Users
        List<UserDB> userDBS = channel.getMembers().stream()
                .map(Member::getUser)
                .map(user -> new UserDB(user.getId(), user.getName(), "#" + user.getDiscriminator()))
                .filter(m -> !userBotDAO.exists(m.getId()))
                .collect(Collectors.toList());

        if (userDBS.size() == 1) {
            userBotDAO.insert(userDBS.get(0));
        } else if (userDBS.size() > 1) {
            userBotDAO.insert(userDBS);
        }

        //Messages -- TODO Get this to work
        MessageHistory messageHistory = channel.getHistoryAfter(lastMessage, 100).complete();
        List<Message> messages = messageHistory.getRetrievedHistory();

        while (!messages.isEmpty()) {
            messages = messageHistory.retrieveFuture(100).complete();

            //Filters out all the messages that already exists
            List<Message> newMessages = messages.stream()
                    .filter(m -> !messageBotDAO.exists(m.getId()))
                    .collect(Collectors.toList());

            List<MessageDB> dbList = newMessages.stream()
                    .map(m -> new MessageDB(m.getId(),
                            userBotDAO.get(m.getAuthor().getId()),
                            m.getContentStripped()))
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
        }
    }
}