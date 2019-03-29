package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.model.objects.Person;
import diplomWork.presenter.EditContactPresenter;
import diplomWork.presenter.IPresenter;
import diplomWork.viewInterface.IEditContact;
import diplomWork.viewInterface.IView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditContacts implements IEditContact {
    private JPanel rootPanel;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTextField nameField;
    private JPanel avatarPanel;
    private JPanel namePanel;
    private JButton saveButton;
    private JLabel editContactsToolTip;
    private JLabel numberField;
    private JTextField surNameField;
    private JPanel avatar;
    BufferedImage logo, trashIcon, phoneLogoImage;
    EditContactPresenter presenter;
    private static EditContacts instance;
    private Person person;

    public static EditContacts getInstance(Person person) {
        if (instance == null) instance = new EditContacts();
        instance.setPresenter(EditContactPresenter.getPresenter(instance));
        instance.setEditUser(person);
        return instance;
    }

   private EditContacts() {       //отработано

        trashIcon = Configs.ICON_TRASH;
        deleteButton.setIcon(new ImageIcon(trashIcon));
        nameField.setBorder(BorderFactory.createEmptyBorder());
        surNameField.setBorder(BorderFactory.createEmptyBorder());
        namePanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        deleteButton.setText(Configs.deleteContactText);
        editContactsToolTip.setText(Configs.editContactText);
        saveButton.setFont(Configs.font22);
        saveButton.setText(Configs.saveButtonText);
       editContactsToolTip.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               ChatForm.getInstance().repaintContactList();
           }
       });
       saveButton.addActionListener(e -> presenter.editContact(numberField.getText(), nameField.getText(), surNameField.getText()));
       deleteButton.addActionListener(e -> presenter.deleteContact(person.getId()));
   }

    private void createUIComponents() {
        mainPanel = new JPanel();
        deleteButton = new JButton(Configs.deleteContactText);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        deleteButton.setBackground(Color.BLACK);
        avatarPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawString("No Image",3,30);
            }
        };
        avatarPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2,true));
        // TODO: place custom component creation code here
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

    public void setPresenter(IPresenter presenter){
       this.presenter = (EditContactPresenter) presenter;
    }

    public void setEditUser(Person person){
        this.person = person;
       nameField.setText(person.getFirstName());
       surNameField.setText(person.getLastName());
       numberField.setText("+" + person.getPhone());
       this.avatar.getGraphics().drawImage(person.getPhotoSmall(), 0,0, avatar.getWidth(), avatar.getHeight(), null);
    }

}
