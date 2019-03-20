package diplomWork.view.components.messages;

import diplomWork.Configs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Incoming extends JPanel {
    private JPanel rootPanel;
    private JLabel left;
    private JLabel top;
    private JLabel bottom;
    private JLabel message;
    private static Color bg = new Color(0, 169, 219);
    private static Color fg = new Color(255,255,255);
    ImageIcon messageOutTop, messageOutRight, messageOutBottom;
    private JLabel dateOfMessage = new JLabel();

    public Incoming(String text, String date){
        try {
            messageOutBottom = new ImageIcon(ImageIO.read(new File("img/message-in-bottom.png")));
            messageOutRight = new ImageIcon(ImageIO.read(new File("img/message-in-left.png")));
            messageOutTop = new ImageIcon(ImageIO.read(new File("img/message-in-top.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.setText(text);
        message.setFont(Configs.font18);
        top.setIcon(messageOutTop);
        bottom.setIcon(messageOutBottom);
        left.setIcon(messageOutRight);
        dateOfMessage.setText(date);
        dateOfMessage.setOpaque(false);
        dateOfMessage.setHorizontalAlignment(SwingConstants.RIGHT);
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
                g.setColor(bg);
                g.fillRect(0,0, this.getWidth(), this.getHeight());
                super.paintComponent(g);
            }
        };
        message.setForeground(fg);
    }
}
