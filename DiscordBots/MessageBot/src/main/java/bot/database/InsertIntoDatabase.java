package bot.database;

import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.MessageDB;
import database.objects.UserDB;
import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class InsertIntoDatabase {
    private final TextChannel TEXT_CHANNEL;
    MessageBotDAO messageBotDAO = new MessageBotDAO();
    UserBotDAO userBotDAO = new UserBotDAO();

    public InsertIntoDatabase(TextChannel textChannel) {
        TEXT_CHANNEL = textChannel;
    }

    public void run() {
        getAllUsersAndInsert();
        getAllMessagesAndInsert();
    }

    private void getAllUsersAndInsert() {
        List<Member> members = TEXT_CHANNEL.getMembers();

        List<UserDB> userDBS = members.stream()
                .map(this::convertToUserDB)
                .filter(m -> !userBotDAO.exists(m.getId()))
                .collect(Collectors.toList());

        userBotDAO.insert(userDBS);
    }

    private void getAllMessagesAndInsert() {
        MessageHistory messageHistory = TEXT_CHANNEL.getHistory();
        List<Message> messages = null;

        while (messages == null || !messages.isEmpty()) {
            messages = messageHistory.retrievePast(100).complete();
            List<Message> newMessages = getMessagesFromMessageHistory(messages);
            List<MessageDB> dbList = newMessages.stream()
                    .map(m ->
                            new MessageDB(m.getId(),
                                    userBotDAO.get(m.getAuthor().getId()),
                                    m.getContentStripped()))
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
        }
    }

    private UserDB convertToUserDB(Member member) {
        User user = member.getUser();

        return new UserDB(user.getId(), user.getName(), "#" + user.getDiscriminator(), member.getNickname());
    }

    private List<Message> getMessagesFromMessageHistory(List<Message> messages) {
        return messages.stream()
                .filter(m -> !messageBotDAO.exists(m.getId()))
                .collect(Collectors.toList());
    }
}
