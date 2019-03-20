package diplomWork.tests;

import diplomWork.view.components.messages.*;
import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JPanel rootPanel;
    private JPanel message;
    private String dateOfMessage = "дата сообщения..."; //временная переменная под дату сообщения

    public ChatPanel(String text, boolean income){
        text = setBreaksInText(text);
        if(income){
            message = new Incoming(text, dateOfMessage);
            rootPanel.add(((Incoming) message).getRootPanel(), BorderLayout.WEST);
        } else {
            message = new Outcoming(text, dateOfMessage);
            rootPanel.add(((Outcoming) message).getRootPanel(), BorderLayout.EAST);
        }

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private String setBreaksInText(String text){    //расстановка переносов через html
        int len = 25;       //длина отрезки
        if(text.length() <= len) return "<html>" + text + "</html>";        //если текст короче длины отрезки - обрамляем его в теги и отдаем обратно

        String result = "";
        int left = 0;       //сдвиг при поиске
        int lastSpace = 0;      //последний пробел
        boolean found = false;  //индикатор найденного пробела

        for (int i = 0; i < text.length();i +=len){
            if(i == 0 && text.length() > len) {
                result = "<html>";
                continue;
            }
            for (int j = 1; j <= len; j++){
                left = i - j;
                if(left%len == 0){      //тот случай, когда слово длиннее ддлины - режем без пробела
                    result = result + text.substring(lastSpace, lastSpace + len + 1) + "<br>";
                    lastSpace += len;
                    found = true;
                    break;
                }
                if(text.charAt(i) == ' '){      //тот случай когда мы сразу попали на пробел
                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, i) + "<br>";
                    lastSpace = i;
                    found = true;
                    break;
                } else if (text.charAt(left) == ' '){   // иначе ищем назад пробел
                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, left) + "<br>";
                    lastSpace = left;
                    found = true;
                    break;
                }
            }
            if(!found){
                result = result + text.substring(lastSpace, i) + "<br>";
                lastSpace = i-1;
                found = false;
            }
        }
        result = result + text.substring(lastSpace + 1) + "</html>";
        return result;
    }
}