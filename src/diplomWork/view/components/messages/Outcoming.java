package diplomWork.view.components.messages;

import diplomWork.Configs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Outcoming extends JPanel {
    private JPanel rootPanel;
    private JLabel right;
    private JLabel top;
    private JLabel bottom;
    private JLabel message;
    private static Color bg2 = new Color(75, 65, 172);
    private static Color fg = new Color(255,255,255);
    ImageIcon messageOutTop, messageOutRight, messageOutBottom;
    private JLabel dateOfMessage = new JLabel();

    public Outcoming(String text, String date){
        try {
            messageOutBottom = new ImageIcon(ImageIO.read(new File("img/message-out-bottom.png")));
            messageOutRight = new ImageIcon(ImageIO.read(new File("img/message-out-right.png")));
            messageOutTop = new ImageIcon(ImageIO.read(new File("img/message-out-top.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.setFont(Configs.font18);
        message.setText(text);
        top.setIcon(messageOutTop);
        bottom.setIcon(messageOutBottom);
        right.setIcon(messageOutRight);
        dateOfMessage.setText(date);
        dateOfMessage.setOpaque(false);
        dateOfMessage.setHorizontalAlignment(SwingConstants.LEFT);
        rootPanel.add(dateOfMessage, BorderLayout.SOUTH);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        message = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(bg2);
                g.fillRect(0,0, this.getWidth(), this.getHeight());
                super.paintComponent(g);
            }
        };
        message.setForeground(fg);
    }
}
