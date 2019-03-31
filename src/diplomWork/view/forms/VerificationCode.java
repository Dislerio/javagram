package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.VerificationCodePresenter;
import diplomWork.viewInterface.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class VerificationCode implements IView {     //отработано
    private JPanel logoPanel;
    private JPanel rootPanel;
    private JLabel labelNull;
    private JLabel numLabel;
    private JLabel phonePict;
    private JLabel textTip;
    private JLabel continueButton;
    private JLabel errLabel;
    private JLabel btnBack;
    private ImageIcon imageIcon;
    private JPasswordField codeField;
    private BufferedImage logo, background, phoneLogoImage, buttonBG;
    private VerificationCodePresenter presenter;
    private static VerificationCode instance;

    public static synchronized VerificationCode getInstance(){
        if(instance == null){
            instance = new VerificationCode();
        }
        instance.setPresenter(VerificationCodePresenter.getPresenter(instance));
        instance.fillPhoneNumber(instance.presenter.getPhoneNumber());
        return instance;
    }

    private VerificationCode() {
        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_LOCK;
        buttonBG = Configs.IMG_BUTTON_BG;
        phonePict.setIcon(new ImageIcon(phoneLogoImage));
        codeField.setBorder(BorderFactory.createEmptyBorder());
        codeField.grabFocus();
        codeField.selectAll();
        textTip.setFont(Configs.font18);
        textTip.setText(Configs.verificationCodeTooltipText);
        numLabel.setFont(Configs.font40);
        btnBack.setIcon(new ImageIcon(Configs.ICON_BACK));
        continueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.checkCode(String.valueOf(codeField.getPassword()));
            }
        });


        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.goBackToPhoneInput();
            }
        });
    }

    public void fillPhoneNumber(String phone){
        numLabel.setText(formatPhoneNumber(phone));
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
        continueButton = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(buttonBG, 0, 3, null);
                super.paintComponent(g);

            }
        };
        continueButton.setForeground(Color.white);
        continueButton.setFont(Configs.getFont(25));
        continueButton.setText(Configs.continueButtonText);

        labelNull = new JLabel();
        labelNull.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.white));
    }

    public void callViewSignUp() {
        RegForm.getInstance();
    }

    @Override
    public void showError(String strError) {
        clearError();
        errLabel.setForeground(Color.RED);
        errLabel.setText(strError);
    }

    @Override
    public void showInfo(String strError) {
        clearError();
        errLabel.setText(strError);
    }

    @Override
    public void clearError() {
        errLabel.setText("");
        errLabel.setForeground(Color.WHITE);
    }

    @Override
    public void showLoadingProcess() {
        codeField.setEnabled(false);
        continueButton.setEnabled(false);
        continueButton.setText("");
        //imageIcon = new ImageIcon(Configs.IMG_LOADING_GIF);       //Todo исправить кнопку на метку
        //imageIcon.setImageObserver(continueButton);
        //continueButton.setDisabledIcon(imageIcon);
    }

    @Override
    public void hideLoadingProcess() {
        continueButton.setIcon(null);
        continueButton.setText(Configs.continueButtonText);
        continueButton.setEnabled(true);
        codeField.setEnabled(true);
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = (VerificationCodePresenter)presenter;
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

}
