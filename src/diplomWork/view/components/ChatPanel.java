package diplomWork.view.components;

import diplomWork.view.components.messages.*;
import org.javagram.response.object.Message;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatPanel extends JPanel {
    private JPanel rootPanel;
    private JPanel messagePanel;
    private String dateOfMessage;
    private int maxLength = 50;
    private String messageText;
    private boolean income;
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("dd.MM.yyyy");

    public ChatPanel (Message message){
        this(message.getMessage(), message.getDate(), !message.isOut());
    }

    public ChatPanel(String text,int dateInt, boolean income) {
        this.messageText = setBreaksInText(text);
        this.income = income;

        Date date = new Date((long)dateInt * 1000);
        if(dateWithoutTime.format(date).equals(dateWithoutTime.format(new Date(System.currentTimeMillis())))){
            this.dateOfMessage = timeFormat.format(date);
        } else {
            this.dateOfMessage = dateFormat.format(date);
        }

        if (income) {
            messagePanel = new Incoming(messageText, dateOfMessage);
            rootPanel.add(((Incoming) messagePanel).getRootPanel(), BorderLayout.WEST);
        } else {
            messagePanel = new Outcoming(messageText, dateOfMessage);
            rootPanel.add(((Outcoming) messagePanel).getRootPanel(), BorderLayout.EAST);
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private String setBreaksInText(String text) {    //расстановка переносов через html
        int len = maxLength;       //длина отрезки
        if (text.length() <= len)
            return "<html>" + text + "</html>";        //если текст короче длины отрезки - обрамляем его в теги и отдаем обратно

        String result = "";
        int left = 0;       //сдвиг при поиске
        int lastSpace = 0;      //последний пробел
        boolean found = false;  //индикатор найденного пробела

        for (int i = 0; i < text.length(); i += len) {
            if (i == 0 && text.length() > len) {
                result = "<html>";
                continue;
            }
            for (int j = 1; j <= len; j++) {
                left = i - j;
                if (left % len == 0) {      //тот случай, когда слово длиннее ддлины - режем без пробела
                    result = result + text.substring(lastSpace, lastSpace + len + 1) + "<br>";
                    lastSpace += len;
                    found = true;
                    break;
                }
                if (text.charAt(i) == ' ') {      //тот случай когда мы сразу попали на пробел
                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, i) + "<br>";
                    lastSpace = i;
                    found = true;
                    break;
                } else if (text.charAt(left) == ' ') {   // иначе ищем назад пробел
                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, left) + "<br>";
                    lastSpace = left;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = result + text.substring(lastSpace, i) + "<br>";
                lastSpace = i - 1;
                found = false;
            }
        }
        result = result + text.substring(lastSpace + 1) + "</html>";
        return result;
    }

//    private String setBreaksInText(String text){    //расстановка переносов через html, версия до 24-03
//        int len = maxLength;       //длина отрезки
//        if(text.length() <= len) return "<html>" + text + "</html>";        //если текст короче длины отрезки - обрамляем его в теги и отдаем обратно
//
//        String result = "";
//        int left = 0;       //сдвиг при поиске
//        int lastSpace = 0;      //последний пробел
//        boolean found = false;  //индикатор найденного пробела
//
//        for (int i = 0; i < text.length();i +=len){
//            if(i == 0 && text.length() > len) {
//                result = "<html>";
//                continue;
//            }
//            for (int j = 1; j <= len; j++){
//                left = i - j;
//                if(left%len == 0){      //тот случай, когда слово длиннее ддлины - режем без пробела
//                    result = result + text.substring(lastSpace, lastSpace + len + 1) + "<br>";
//                    lastSpace += len;
//                    found = true;
//                    break;
//                }
//                if(text.charAt(i) == ' '){      //тот случай когда мы сразу попали на пробел
//                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, i) + "<br>";
//                    lastSpace = i;
//                    found = true;
//                    break;
//                } else if (text.charAt(left) == ' '){   // иначе ищем назад пробел
//                    result = result + text.substring(lastSpace == 0 ? lastSpace : lastSpace + 1, left) + "<br>";
//                    lastSpace = left;
//                    found = true;
//                    break;
//                }
//            }
//            if(!found){
//                result = result + text.substring(lastSpace, i) + "<br>";
//                lastSpace = i-1;
//                found = false;
//            }
//        }
//        result = result + text.substring(lastSpace + 1) + "</html>";
//        return result;
//    }

}