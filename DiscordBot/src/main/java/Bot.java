import commands.GetMessageCount;
import commands.WriteMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault("NzY0OTU5MDk1NDg3OTg3NzMz.X4N1rA.aoO_TijV1zJuJcSj1n6AnwyE3JA");
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
