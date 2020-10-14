package bot.commands;

import bot.messages.MessagesUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetMessageCount extends ListenerAdapter {
    JDA jda;

    public GetMessageCount(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        TextChannel channel = event.getChannel();

        if (msg.equals("!getMsgCount")) {
            channel.sendMessage("This is the message count command").queue();
            return;
        }

        if (msg.contains("!getMsgCount") && msg.length() > 12) {
            //12
            channel = event.getChannel();
            String username = msg.substring(12).strip();
            List<Member> members = event.getChannel().getMembers();

            Member member = members.stream()
                    .filter(n -> n.getEffectiveName().equals(username))
                    .findAny().orElse(null);
            MessagesUtil messagesUtil = new MessagesUtil(channel);

            if (member != null) {
                int numOfMsg = messagesUtil.countMessages(member.getUser());

                channel.sendMessage(member.getEffectiveName() + " has sent " + numOfMsg + "bot/messages").queue();
            } else
                channel.sendMessage("User not found").queue();
        }
    }
}
