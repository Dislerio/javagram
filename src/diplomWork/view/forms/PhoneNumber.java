package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.PhoneNumberPresenter;
import diplomWork.viewInterface.IPhoneNumber;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.ParseException;

public class PhoneNumber implements IPhoneNumber {  //++
    private JPanel rootPanel;
    private JButton continueButton;
    private JPanel logoPanel;
    private JLabel text;
    private JFormattedTextField numberField;
    private JLabel phoneLogo;
    private JPanel numPanel;
    private JLabel label1;
    private JLabel errLabel;
    private JLabel label2;
    ImageIcon imageIcon;
    BufferedImage logo;
    BufferedImage background;
    BufferedImage phoneLogoImage;
    PhoneNumberPresenter presenter;

    public PhoneNumber(){   //отработано
        logo = Configs.LOGO;
        background = Configs.BG_IMAGE;
        phoneLogoImage = Configs.ICON_PHONE;
        text.setFont(Configs.getFont(18));
        text.setText(Configs.phoneNumberTooltipText);
        text.setBorder(BorderFactory.createEmptyBorder(50,0,10,0));
        continueButton.setBackground(new Color(0,178,226));
        continueButton.setForeground(Color.white);
        continueButton.setFont(Configs.getFont(25));
        continueButton.setText(Configs.continueButtonText);

        phoneLogo.setIcon(new ImageIcon(phoneLogoImage));
        numberField.setBorder(BorderFactory.createEmptyBorder());
        numberField.setFont(Configs.getFont(32));
        label1.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        continueButton.addActionListener(e -> {
            presenter.checkPhone(numberField.getText());
        });
        setNumberFieldMask(numberField);
        numberField.setText("9996624444");

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

    private void setNumberFieldMask(JFormattedTextField numberField) {
        MaskFormatter maskFormatter;
        try {
            maskFormatter = new MaskFormatter(Configs.INTERFACE_PHONE_MASK);
            maskFormatter.setPlaceholder(null);
            maskFormatter.setPlaceholderCharacter(Configs.INTERFACE_PHONE_MASK_PLACEHOLDER);
            numberField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        numberField.setEnabled(false);
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
        numberField.setEnabled(true);
    }

    @Override
    public void showPhoneFormatError(String strError) {
        //TODO
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void fillPhoneNumberTextField(String phone) {
        numberField.setText(phone);
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = (PhoneNumberPresenter) presenter;
    }
}
