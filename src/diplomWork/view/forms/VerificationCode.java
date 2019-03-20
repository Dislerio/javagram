package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.VerificationCodePresenter;
import diplomWork.viewInterface.IVerificationCode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class VerificationCode implements IVerificationCode {     //отработано
    private JPanel logoPanel;
    private JPanel rootPanel;
    private JLabel labelNull;
    private JLabel numLabel;
    private JLabel phonePict;
    private JLabel textTip;
    private JPasswordField passwordField;
    private JButton continueButton;
    private BufferedImage logo, background, phoneLogoImage;
    private VerificationCodePresenter verificationCodePresenter;

    public VerificationCode(String phoneNumber) {
        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_LOCK;
        phonePict.setIcon(new ImageIcon(phoneLogoImage));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.selectAll();
        continueButton.setBorder(BorderFactory.createLineBorder(new Color(0, 178, 230), 15, true));
        textTip.setFont(Configs.font18);
        textTip.setText(Configs.verificationCodeTooltipText);
        numLabel.setText(phoneNumber);
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

    @Override
    public void showError(String strError) {

    }

    @Override
    public void clearError() {

    }

    @Override
    public void showLoadingProcess() {

    }

    @Override
    public void hideLoadingProcess() {

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public boolean isCodeValid(String code) {
        return false;
    }

    public JButton getContinueButton(){
        return continueButton;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.verificationCodePresenter = (VerificationCodePresenter)presenter;
    }
}
