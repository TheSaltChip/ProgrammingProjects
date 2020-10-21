package bot.database;

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

            List<MessageDB> dbList = messages.stream()
                    .map(m -> new MessageDB(m.getId(),
                            userBotDAO.get(m.getAuthor().getId()),
                            m.getContentStripped(), m.getTimeCreated().toLocalDateTime()))
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
        }
    }

    public boolean hasRun() {
        return !(userBotDAO.get().isEmpty() || messageBotDAO.get().isEmpty());
    }

    //TODO -- Get the latest message from the database and search from that
    public void update(TextChannel channel) {
        //Users
        List<UserDB> userDBS = channel.getMembers().stream()
                .map(Member::getUser)
                .filter(u -> !userBotDAO.exists(u.getId()))
                .map(user -> new UserDB(user.getId(), user.getName(), "#" + user.getDiscriminator()))
                .collect(Collectors.toList());

        if (userDBS.size() == 1) {
            userBotDAO.insert(userDBS.get(0));
        } else if (userDBS.size() > 1) {
            userBotDAO.insert(userDBS);
        }

        //Message
        MessageHistory messageHistory = null;
        List<Message> messages = null;

        while (true) {
            if (messages == null) {
                messageHistory = channel.getHistoryAfter(messageBotDAO.mostRecent().getId(), 100).complete();
                messages = messageHistory.getRetrievedHistory();
            } else {
                messages = messageHistory.retrieveFuture(100).complete();
            }

            if (messages.size() == 0) break;

            List<MessageDB> dbList = messages.stream()
                    .map(m -> new MessageDB(m.getId(),
                            userBotDAO.get(m.getAuthor().getId()),
                            m.getContentStripped(), m.getTimeCreated().toLocalDateTime()))
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
            userBotDAO.update(dbList);
        }
    }
}