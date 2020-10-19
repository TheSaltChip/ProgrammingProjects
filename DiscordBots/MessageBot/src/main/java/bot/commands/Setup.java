package bot.commands;

import bot.database.InsertIntoDatabase;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Setup extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        String msg = message.getContentStripped();
        TextChannel channel = event.getChannel();
        User user = message.getAuthor();

        if (msg.equals("!setup") && user.getName().equals("TheSaltChip") && user.getDiscriminator().equals("8756")) {
            InsertIntoDatabase intoDatabase = new InsertIntoDatabase(channel);
            channel.sendMessage("Initiating data gathering").queue();
            intoDatabase.run();
            channel.sendMessage("Data gathering complete").queue();
        } else if (msg.equals("!setup") && !user.getName().equals("TheSaltChip") && user.getDiscriminator().equals("8756")) {
            channel.sendMessage("Only \"TheSaltChip#8756\" is allowed to use this command").queue();
        }
    }
}
