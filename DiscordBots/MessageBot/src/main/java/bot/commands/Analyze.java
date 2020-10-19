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

        if (m.getContentStripped().strip().startsWith("!analyze")) {

            if (m.getContentStripped().length() == 8) {
                event.getChannel().sendMessage("This is the !analyze command\n" +
                        "To use this command write @ and pick the user you want to analyze.\n").queue();

            } else if (m.getContentStripped().strip().length() > 8 && m.getContentStripped().strip().charAt(8) == ' ') {
                if (m.getMentionedMembers().size() == 1) {
                    AnalyzeDatabase aD = new AnalyzeDatabase(event.getChannel(), event.getMessage().getMentionedUsers().get(0));

                    if (!aD.exists()) {
                        event.getChannel().sendMessage("User not found in the Database, run !setup to update the database").queue();

                    } else {
                        event.getChannel().sendMessage(aD.computeLetters() + "").queue();
                    }
                }
            }
        }


    }
}
