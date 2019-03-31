package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.AddContactPresenter;
import diplomWork.presenter.IPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AddContactsForm implements IView {
    private BufferedImage phoneIcon;
    private BufferedImage editIcon;
    private BufferedImage deleteIcon;
    private JButton addButton;
    private JLabel addContactsText;
    private JLabel btnBack;
    private JLabel textTip;
    private JLabel phoneLogo;
    private JLabel findUserLabel;
    private JLabel findUserRes;
    private JPanel numPanel;
    private JPanel rootPanel;
    private JPanel searchRes;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField numberField;
    private AddContactPresenter presenter;
    private static AddContactsForm instance;

    public synchronized static AddContactsForm getInstance(){
        if(instance == null) instance = new AddContactsForm();
        instance.clearFields();
        instance.setPresenter(AddContactPresenter.getPresenter(instance));
        return instance;
    }

    private AddContactsForm() {      //отработано

        phoneIcon = Configs.ICON_PHONE;
        editIcon = Configs.ICON_EDIT;
        deleteIcon = Configs.ICON_TRASH;

        btnBack.setFont(Configs.getFont(26));
        phoneLogo.setIcon(new ImageIcon(phoneIcon));
        numberField.setBorder(BorderFactory.createEmptyBorder());
        numPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        textTip.setFont(Configs.getFont(16));
        textTip.setText(Configs.addContactsToolTip);
        addContactsText.setText(Configs.addContactsText);
        addContactsText.setFont(Configs.getFont(40));
        searchRes.setVisible(false);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.addContact(numberField.getText(), firstName.getText(),
                        lastName.getText().trim());
                presenter.goToMainForm();
            }
        });
        btnBack.setText("");
        btnBack.setIcon(new ImageIcon(Configs.ICON_BACK));
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.goToMainForm();
            }
        });
    }

    private void clearFields(){
        numberField.setText("");
        firstName.setText("");
        lastName.setText("");
    }

    private void createUIComponents() {
        numPanel = new JPanel();
        // TODO: place custom component creation code here
    }

    @Override
    public void setPresenter(IPresenter af) {
        this.presenter = (AddContactPresenter)af;
    }

    @Override
    public void showInfo(String info) {
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

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
