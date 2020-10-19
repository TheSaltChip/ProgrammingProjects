import bot.commands.Analyze;
import bot.commands.SetupDatabase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) throws IOException {
        String token = new Scanner(new File("src\\BotTokens\\MB.txt")).nextLine();
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        JDA jda;

        try {
            jda = jdaBuilder.build();
            jda.addEventListener(new SetupDatabase());
            jda.addEventListener(new Analyze());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
