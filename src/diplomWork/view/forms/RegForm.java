package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.RegisterUserPresenter;
import diplomWork.viewInterface.IRegisterUser;
import diplomWork.viewInterface.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RegForm implements IRegisterUser {
    private BufferedImage logo, background, avatarNoImage;
    private JPanel rootPanel;
    private JLabel textTip;
    private JPanel avatarField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JPanel logoPanel;
    private JButton regButton;
    private static RegForm instance;
    private RegisterUserPresenter presenter;

    public static RegForm getInstance(){
        if(instance == null){
            instance = new RegForm();
        }
        instance.setPresenter(RegisterUserPresenter.getInstance(instance));
        return instance;
    }

    private RegForm() { //отработано

        logo = Configs.LOGO_MINI;
        background = Configs.BG_IMAGE;
        avatarNoImage = Configs.NO_AVATAR;

        surnameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        nameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        avatarField.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        // listeners
        regButton.addActionListener( e -> {
                presenter.signUp(nameTextField.getText().trim(), surnameTextField.getText().trim());
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

    public void callViewChat() {
        ChatForm.getInstance();
    }
}
