package diplomWork.view.components.messages;

import diplomWork.Configs;

import javax.swing.*;
import java.awt.*;

public class Outcoming extends JPanel {
    private JPanel rootPanel;
    private JLabel right;
    private JLabel message;
    private JPanel pnlMessage;
    private static Color bg2 = new Color(75, 65, 172);
    private static Color fg = new Color(255, 255, 255);
    private ImageIcon messageOutTop, messageOutRight, messageOutBottom;
    private JLabel dateOfMessage;
    private int radius = 10;
    private int maxLineLength;
    private Font f = Configs.getFont(18);

    public Outcoming(String text, String dateText) {


        message.setFont(f);
        message.setText(text);
        message.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 15));       // отступы!!!
        dateOfMessage.setText(dateText);
        dateOfMessage.setHorizontalAlignment(SwingConstants.LEFT);
        rootPanel.add(dateOfMessage, BorderLayout.SOUTH);

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
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

