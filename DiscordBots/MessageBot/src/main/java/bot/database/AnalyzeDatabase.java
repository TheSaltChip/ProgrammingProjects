package bot.database;

import database.dao.info.InfoBotDAO;
import database.dao.message.MessageBotDAO;
import database.dao.user.UserBotDAO;
import database.objects.MessageDB;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnalyzeDatabase {
    private final TextChannel TEXT_CHANNEL;
    private final User USER;
    private final MessageBotDAO MESSAGE_BOT_DAO = new MessageBotDAO();
    private final UserBotDAO USER_BOT_DAO = new UserBotDAO();
    private final InfoBotDAO INFO_BOT_DAO = new InfoBotDAO();

    public AnalyzeDatabase(TextChannel text_channel, User user) {
        TEXT_CHANNEL = text_channel;
        USER = user;
    }

    public Map<Character, Integer> computeLetters() {
        List<String> messages = USER_BOT_DAO.get(USER.getId()).getMessages().stream().map(MessageDB::getMsg_content).collect(Collectors.toList());
        Map<Character, Integer> letters = new TreeMap<>(
                Comparator.comparingInt(c -> c)
        );

        messages.forEach(m -> m.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> {
                    if (!letters.containsKey(c)) {
                        letters.put(c, 1);
                    } else {
                        letters.compute(c, (k, i) -> (i == null) ? 1 : i + 1);
                    }
                })
        );

        return letters;

    }

    public boolean exists() {
        return USER_BOT_DAO.exists(USER.getId());
    }

    public Map<Character, Integer> getLetters() {
        return INFO_BOT_DAO.getLetters(USER.getId());
    }

    public void insert(Map<Character, Integer> lettersInfo) {
        INFO_BOT_DAO.insert(lettersInfo);
    }
}
