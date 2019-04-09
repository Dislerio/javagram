package diplomWork.view.components;

import diplomWork.view.components.messages.Incoming;
import diplomWork.view.components.messages.Outcoming;
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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy в HH:mm:ss");
    private static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("dd.MM.yyyy");

    public ChatPanel(Message message) {
        this(message.getMessage(), message.getDate(), !message.isOut());
    }

    private ChatPanel(String text, int dateInt, boolean income) {
        this.messageText = setBreaks(text);
        this.income = income;

        Date date = new Date((long) dateInt * 1000);
        if (dateWithoutTime.format(date).equals(dateWithoutTime.format(new Date(System.currentTimeMillis())))) {
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

    private String setBreaks(String text) {
        text = text.replaceAll("\n", "<br>");
        return ("<html><p style=\"width:350px\">" + text + "</p></html>");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


}