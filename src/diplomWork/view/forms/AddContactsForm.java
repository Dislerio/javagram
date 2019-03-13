package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.AddContactPresenter;
import diplomWork.viewInterface.AddContactInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AddContactsForm implements AddContactInterface {
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


    public AddContactsForm() {      //отработано

        phoneIcon = Configs.ICON_PHONE;
        editIcon = Configs.ICON_EDIT;
        deleteIcon = Configs.ICON_TRASH;

        phoneLogo.setIcon(new ImageIcon(phoneIcon));
        numberField.setBorder(BorderFactory.createEmptyBorder());
        numPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.white));
        textTip.setFont(Configs.font16);
        textTip.setText(Configs.addContactsToolTip);
        addContactsText.setText(Configs.addContactsText);
        addContactsText.setFont(Configs.font40);

    }

    @Override
    public void setPresenter(AddContactPresenter af) {
        this.presenter = af;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        numPanel = new JPanel();
        // TODO: place custom component creation code here
    }

    public Component getAddButton() {
        return addButton;
    }
}
