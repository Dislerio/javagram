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
    private JLabel message;
    private JPanel pnlMessage;
    private static Color bg2 = new Color(75, 65, 172);
    private static Color fg = new Color(255, 255, 255);
    ImageIcon messageOutTop, messageOutRight, messageOutBottom;
    private JLabel dateOfMessage;
    int radius = 10;
    private int maxLineLength;
    Font f = Configs.font18;

    public Outcoming(String text, String dateText) {


        message.setFont(f);
        message.setText(text);
        message.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));       // отступы!!!


        dateOfMessage.setText(dateText);
        dateOfMessage.setHorizontalAlignment(SwingConstants.LEFT);
        rootPanel.add(dateOfMessage, BorderLayout.SOUTH);

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dateOfMessage = new JLabel();
        message = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(bg2);
                g.fillRoundRect(0, 0, this.getWidth() - radius, this.getHeight(), radius, radius);
                int[] y = {this.getHeight() / 2, this.getHeight() / 2 - radius / 2, this.getHeight() / 2 + radius / 2};
                int[] x = {this.getWidth(), this.getWidth() - radius, this.getWidth() - radius};
                g.fillPolygon(x, y, 3);
                super.paintComponent(g);
            }
        };
        message.setForeground(fg);
    }

}

