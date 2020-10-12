package commands;

import messages.MessagesUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WriteMessages  extends ListenerAdapter {
    JDA jda;

    public WriteMessages(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        String msg = event.getMessage().getContentRaw();
        TextChannel textChannel = event.getChannel();

        if(msg.equals("!writeMessages")){
            textChannel.sendMessage("This is the writeMessage command").queue();
            return;
        }

        if(msg.contains("!writeMessages")){
            String amountString = msg.substring(14).strip();

            int amount;

            try {
                amount = Integer.parseInt(amountString);
            } catch (NumberFormatException e){
                textChannel.sendMessage("Argument must be an integer").queue();
                return;
            }

            MessagesUtil messagesUtil = new MessagesUtil(textChannel);
            int n = messagesUtil.countMessages(jda.getSelfUser());

            for (int i = n+1; i <= n + amount; i++) {
                textChannel.sendMessage(i + "").queue();
            }
        }

    }


}
