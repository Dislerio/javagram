package diplomWork.view.forms;

import diplomWork.Configs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RegForm {
    private BufferedImage logo, background, avatarNoImage;
    private JPanel rootPanel;
    private JLabel textTip;
    private JPanel avatarField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JPanel logoPanel;
    private JButton RegButton;

    public RegForm() { //отработано

        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        avatarNoImage = Configs.NO_AVATAR;

        surnameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        nameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        avatarField.setBorder(BorderFactory.createLineBorder(Color.white, 2));

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        rootPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        avatarField = new JPanel() {
            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                g.drawImage(avatarNoImage, 0, 0, avatarField.getWidth(), avatarField.getHeight(), null);
            }
        };
        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 15, 20, null);
            }
        };
        logoPanel.setOpaque(false);


    }
}
