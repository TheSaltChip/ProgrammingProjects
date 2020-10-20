package bot.commands;

import bot.database.DatabaseUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Setup extends ListenerAdapter {
    private final DatabaseUtil SETUP = new DatabaseUtil();
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        String msg = message.getContentStripped();
        TextChannel channel = event.getChannel();
        User user = message.getAuthor();

        if (msg.equals("!setup")) {
            if (user.getName().equals("TheSaltChip") && user.getDiscriminator().equals("8756")) {
                if (SETUP.hasRun()) {
                    channel.sendMessage("The channel has already been setup\nUse !update to update the values").queue();
                    return;
                }

                channel.sendMessage("Initiating data gathering").queue();
                SETUP.setup(channel);
                channel.sendMessage("Data gathering complete").queue();
            } else {
                channel.sendMessage("Only \"TheSaltChip#8756\" is allowed to use this command").queue();
            }
        } else if (msg.equals("!update")) {
            if(user.getName().equals("TheSaltChip") && user.getDiscriminator().equals("8756")){
                channel.sendMessage("Starting update").queue();
                SETUP.update(channel);
                channel.sendMessage("Update finished").queue();
            } else {
                channel.sendMessage("Only \"TheSaltChip#8756\" is allowed to use this command").queue();
            }
        }
    }
}
