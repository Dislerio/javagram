package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.RegisterUserPresenter;
import diplomWork.viewInterface.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RegForm implements IView {
    private BufferedImage logo, background, avatarNoImage;
    private JButton regButton;
    private JLabel btnBack, errLabel, textTip;
    private JPanel avatarField, logoPanel, rootPanel;
    private JTextField nameTextField, surnameTextField;
    private static RegForm instance;
    private RegisterUserPresenter presenter;

    public static RegForm getInstance(){
        if(instance == null){
            instance = new RegForm();
        }
        instance.setPresenter(RegisterUserPresenter.getPresenter(instance));
        return instance;
    }

    private RegForm() {
        setPresenter(RegisterUserPresenter.getPresenter(this));     //костыль1

        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        avatarNoImage = Configs.NO_AVATAR;

        btnBack.setIcon(new ImageIcon(Configs.ICON_BACK));
        surnameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        nameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        avatarField.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        // listeners
        regButton.addActionListener( e -> {
                presenter.signUp(nameTextField.getText().trim(), surnameTextField.getText().trim());
        });
        btnBack.addMouseListener(new MouseAdapter() {
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

    @Override
    public void setPresenter(IPresenter af) {
        this.presenter = (RegisterUserPresenter)af;
    }

    @Override
    public void showError(String strError) {
        clearError();
        errLabel.setForeground(Color.RED);
        errLabel.setText(strError);
    }

    @Override
    public void clearError() {
        errLabel.setText("");
        errLabel.setForeground(Color.WHITE);
    }

    @Override
    public void showLoadingProcess() {      //Todo сделать через метку вместо кнопки
        nameTextField.setEnabled(false);
        surnameTextField.setEnabled(false);
        regButton.setEnabled(false);
        //continueButton.setText("");
//        imageIcon = new ImageIcon(Configs.IMG_LOADING_GIF);
//        imageIcon.setImageObserver(continueButton);
//        continueButton.setDisabledIcon(imageIcon);
    }

    @Override
    public void hideLoadingProcess() {
        nameTextField.setEnabled(true);
        surnameTextField.setEnabled(true);
        regButton.setEnabled(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void goBackToPhoneInput(){
        PhoneNumber.getInstance();
    }
}
