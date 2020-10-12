package messages;

import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MessagesUtil {
    private final TextChannel TEXT_CHANNEL;

    public MessagesUtil(TextChannel textChannel) {
        TEXT_CHANNEL = textChannel;
    }

    public List<Message> getMessages(User user) {
        CompletableFuture<List<Message>> messages = TEXT_CHANNEL.getIterableHistory().takeWhileAsync(Objects::nonNull);

        try {
            return messages.get().stream()
                    .filter(m -> m.getAuthor().equals(user))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int countMessages(User user) {
        List<Message> m = getMessages(user);
        return m != null ? getMessages(user).size() : 0;
    }
}
