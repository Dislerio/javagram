package diplomWork.view.forms;

import diplomWork.Configs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class VerificationCode {     //отработано
    private JPanel logoPanel;
    private JPanel rootPanel;
    private JLabel labelNull;
    private JLabel numLabel;
    private JLabel phonePict;
    private JLabel textTip;
    private JPasswordField passwordField;
    private JButton continueButton;
    private BufferedImage logo, background, phoneLogoImage;

    public VerificationCode() {
        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_LOCK;

        phonePict.setIcon(new ImageIcon(phoneLogoImage));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.selectAll();
        continueButton.setBorder(BorderFactory.createLineBorder(new Color(0, 178, 230), 15, true));
        textTip.setFont(Configs.font18);
        textTip.setText(Configs.verificationCodeTooltipText);
        numLabel.setFont(Configs.font40);
    }

    private void createUIComponents() {
        rootPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        textTip = new JLabel();

        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 10, 10, null);
            }
        };
        labelNull = new JLabel();
        labelNull.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.white));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
