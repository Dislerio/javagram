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
    private JLabel message;
    private JLabel dateOfMessage = new JLabel();
    private static Color bg = new Color(0, 169, 219);
    private static Color fg = new Color(255, 255, 255);
    ImageIcon messageOutTop, messageOutRight, messageOutBottom;
    int radius = 10;

    public Incoming(String text, String dateText) {

        message.setText(text);
        message.setFont(Configs.font18);
        message.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));


        dateOfMessage.setText(dateText);
        dateOfMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        rootPanel.add(dateOfMessage, BorderLayout.SOUTH);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        message = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(bg);
                g.fillRoundRect(radius, 0, this.getWidth() - radius, this.getHeight(), radius, radius);
                int[] y = {this.getHeight() / 2, this.getHeight() / 2 - radius / 2, this.getHeight() / 2 + radius / 2};
                int[] x = {0, radius, radius};
                g.fillPolygon(x, y, 3);
                super.paintComponent(g);
            }
        };
        message.setForeground(fg);
    }

}