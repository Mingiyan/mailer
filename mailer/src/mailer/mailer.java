package mailer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/**
 * Created by mdordzhiev on 24.08.2016.
 */
public class mailer {
    private static Sender otpravit = new Sender("your@mail.ru","abrakadabra");
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            URL x5 = new URL("https://somepage.ru/mira/status.jsp");
            BufferedReader in = new BufferedReader(new InputStreamReader(x5.openStream()));
            StringBuilder text = new StringBuilder();
            String newLine = System.getProperty("line.separator");


            String inputLine;
            String needLine = "Search server status: Error";
            while ((inputLine = in.readLine()) != null) {
                text.append(inputLine);
                text.append(newLine);
            }
            text.toString();
            in.close();


            if (text.indexOf(needLine) != -1) {
                otpravit.send("Внимание! Сервер поиска упал!", "Провертье работу сервера поиска! Ошибка <Search server status: Error>", "your@mail.ru", "tomail@mail.ru");
            }
            TimeUnit.MINUTES.sleep(5);
        }
        }
}


