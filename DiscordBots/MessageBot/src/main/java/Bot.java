import commands.GetMessageCount;
import commands.WriteMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) throws IOException {
        String token = new Scanner(new File("src\\BotTokens\\MAB.txt")).nextLine();
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        JDA jda;

        try {
            jda = jdaBuilder.build();
            jda.addEventListener(new GetMessageCount(jda));
            jda.addEventListener(new WriteMessages(jda));

        } catch (LoginException e) {
            e.printStackTrace();
        }


    }
}
