package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.AddContactPresenter;
import diplomWork.presenter.IPresenter;
import diplomWork.viewInterface.IAddContact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AddContactsForm implements IAddContact {
    private BufferedImage phoneIcon;
    private BufferedImage editIcon;
    private BufferedImage deleteIcon;
    private JButton addButton;
    private JLabel textTip;
    private JPanel rootPanel;
    private JPanel numPanel;
    private JLabel phoneLogo;
    private JTextField numberField;
    private JPanel searchRes;
    private JLabel findUserLabel;
    private JLabel findUserRes;
    private JLabel addContactsText;
    private AddContactPresenter presenter;
    private static AddContactsForm instance;

    public static AddContactsForm getInstance(){
        if(instance == null){
            instance = new AddContactsForm();
        }
        return instance;
    }

    private AddContactsForm() {      //отработано

        phoneIcon = Configs.ICON_PHONE;
        editIcon = Configs.ICON_EDIT;
        deleteIcon = Configs.ICON_TRASH;

        phoneLogo.setIcon(new ImageIcon(phoneIcon));
        numberField.setBorder(BorderFactory.createEmptyBorder());
        numPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        textTip.setFont(Configs.getFont(16));
        textTip.setText(Configs.addContactsToolTip);
        addContactsText.setText(Configs.addContactsText);
        addContactsText.setFont(Configs.getFont(40));

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.goToMainForm();
            }
        });
    }

    @Override
    public void setPresenter(IPresenter af) {
        this.presenter = (AddContactPresenter)af;
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

    private void createUIComponents() {
        numPanel = new JPanel();
        // TODO: place custom component creation code here
    }

}
