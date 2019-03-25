package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.model.TLHandler;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.VerificationCodePresenter;
import diplomWork.viewInterface.IVerificationCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class VerificationCode implements IVerificationCode {     //отработано
    private JPanel logoPanel;
    private JPanel rootPanel;
    private JLabel labelNull;
    private JLabel numLabel;
    private JLabel phonePict;
    private JLabel textTip;
    private JPasswordField codeField;
    private JButton continueButton;
    private JLabel errLabel;
    private BufferedImage logo, background, phoneLogoImage;
    private VerificationCodePresenter presenter;
    ImageIcon imageIcon;


    public VerificationCode() {
        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_LOCK;
        phonePict.setIcon(new ImageIcon(phoneLogoImage));
        codeField.setBorder(BorderFactory.createEmptyBorder());
        codeField.selectAll();
        continueButton.setBorder(BorderFactory.createLineBorder(new Color(0, 178, 230), 15, true));
        textTip.setFont(Configs.font18);
        textTip.setText(Configs.verificationCodeTooltipText);
        numLabel.setText(TLHandler.getInstance().getUserPhone());
        numLabel.setFont(Configs.font40);

        continueButton.addActionListener(e -> {
            presenter.checkCode(String.valueOf(codeField.getPassword()));     //Todo
        });
        errLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.goBackToPhoneInput();
            }
        });
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
        imageIcon = Configs.IMG_LOADING_GIF;
        imageIcon.setImageObserver(continueButton);
        continueButton.setDisabledIcon(imageIcon);
    }

    @Override
    public void hideLoadingProcess() {
        continueButton.setIcon(null);
        continueButton.setText(Configs.continueButtonText);
        continueButton.setEnabled(true);
        codeField.setEnabled(true);
    }

    @Override
    public void setPhoneNumber(String phone){
        String phoneFormat = "+" + phone.substring(0,1) + " (" + phone.substring(1,4) + ") " + phone.substring(4);
        numLabel.setText(phoneFormat);
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = (VerificationCodePresenter)presenter;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
