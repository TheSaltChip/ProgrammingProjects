package bot.commands;

import bot.database.AnalyzeDatabase;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Analyze extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message m = event.getMessage();

        if (m.getContentStripped().strip().equals("!analyze")) {

            if (m.getContentStripped().length() == 8) {
                event.getChannel().sendMessage("This is the !analyze command\n" +
                        "To use this command write the username of the person you want to analyze\n" +
                        "after !analyze. You may also @ the person.\n").queue();

            } else if (m.getContentStripped().strip().length() > 9) {
                if (m.getMentionedMembers().size() == 1) {
                    AnalyzeDatabase aD = new AnalyzeDatabase(event.getChannel(), event.getMessage().getMentionedUsers().get(0));

                    if (!aD.exists()) {
                        event.getChannel().sendMessage("User not found in the Database, run !setup to update the database").queue();

                    } else {

                    }
                }

            }
        }


    }
}
