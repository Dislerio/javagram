package diplomWork.view.forms;

import diplomWork.Configs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PhoneNumber {
    private JPanel rootPanel;
    private JButton continueButton;
    private JPanel logoPanel;
    private JLabel text;
    private JTextField numberField;
    private JLabel phoneLogo;
    private JPanel numPanel;
    private JLabel label1;
    private JLabel label2;
    BufferedImage logo;
    BufferedImage background;
    BufferedImage phoneLogoImage;

    public PhoneNumber(){   //отработано
        logo = Configs.LOGO;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_PHONE;

        text.setFont(Configs.font18);
        text.setText(Configs.phoneNumberTooltipText);
        text.setBorder(BorderFactory.createEmptyBorder(50,0,10,0));
        continueButton.setBackground(new Color(0,178,226));
        continueButton.setForeground(Color.white);
        continueButton.setFont(Configs.font25);
        phoneLogo.setIcon(new ImageIcon(phoneLogoImage));
        numberField.setBorder(BorderFactory.createEmptyBorder());
        numberField.setFont(Configs.font32);
        label1.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

    }

    private void createUIComponents() {
        rootPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0,0, null);
            }
        };
        logoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 18,10, null);
            }
        };
        numPanel = new JPanel();
        numPanel.setBorder(BorderFactory.createMatteBorder(0,0,3, 0 ,(new Color (255,255,255))));


    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
