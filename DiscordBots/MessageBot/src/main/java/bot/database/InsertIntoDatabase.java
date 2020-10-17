package bot.database;

import database.DAO.member.MemberBotDAO;
import database.DAO.message.MessageBotDAO;
import database.objects.MemberDB;
import database.objects.MessageDB;
import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class InsertIntoDatabase {
    private final TextChannel TEXT_CHANNEL;
    MessageBotDAO messageBotDAO = new MessageBotDAO();
    MemberBotDAO memberBotDAO = new MemberBotDAO();

    public InsertIntoDatabase(TextChannel textChannel) {
        TEXT_CHANNEL = textChannel;
    }

    public void run() {
        getAllUsersAndInsert();
        getAllMessagesAndInsert();
    }

    private void getAllUsersAndInsert() {
        List<Member> members = TEXT_CHANNEL.getMembers();

        List<MemberDB> memberDBS = members.stream()
                .map(this::convertToMemberDB)
                .filter(m -> !memberBotDAO.exists(m.getUser_id()))
                .collect(Collectors.toList());

        memberBotDAO.insert(memberDBS);
    }

    private void getAllMessagesAndInsert() {
        MessageHistory messageHistory = TEXT_CHANNEL.getHistory();
        List<Message> messages = null;

        while (messages == null || !messages.isEmpty()) {
            messages = messageHistory.retrievePast(100).complete();
            List<Message> newMessages = getMessagesFromMessageHistory(messages);
            List<MessageDB> dbList = newMessages.stream()
                    .map(this::convertToMessageDB)
                    .collect(Collectors.toList());

            messageBotDAO.insert(dbList);
        }
    }

    private MemberDB convertToMemberDB(Member member) {
        User user = member.getUser();

        MemberDB memberDB = new MemberDB();
        memberDB.setUser_id(user.getId());
        memberDB.setUsername(user.getName());
        memberDB.setDiscriminator("#" + user.getDiscriminator());
        memberDB.setNickname(member.getNickname());

        return memberDB;
    }

    private List<Message> getMessagesFromMessageHistory(List<Message> messages) {
        return messages.stream()
                .filter(m -> !messageBotDAO.exists(m.getId()))
                .collect(Collectors.toList());
    }

    private MessageDB convertToMessageDB(Message message) {
        MessageDB messageDB = new MessageDB();
        messageDB.setMessage_id(message.getId());
        messageDB.setAuthor(memberBotDAO.get(message.getAuthor().getId()));
        messageDB.setMsg_content(message.getContentStripped());

        return messageDB;
    }
}
