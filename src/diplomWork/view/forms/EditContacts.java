package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.objects.Person;
import diplomWork.presenter.EditContactPresenter;
import diplomWork.presenter.IPresenter;
import diplomWork.view.components.TransparentBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class EditContacts extends TransparentBackground implements IView {
    private JButton deleteButton;
    private JButton saveButton;
    private JPanel avatar;
    private JPanel avatarPanel;
    private JPanel mainPanel;
    private JPanel namePanel;
    private JPanel rootPanel;
    private JLabel editContactsToolTip;
    private JLabel numberField;
    private JTextField nameField;
    private JTextField surNameField;
    private JLabel btnBack;
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

   private EditContacts() {

        trashIcon = Configs.ICON_TRASH;
        deleteButton.setIcon(new ImageIcon(trashIcon));
        nameField.setBorder(BorderFactory.createEmptyBorder());
        surNameField.setBorder(BorderFactory.createEmptyBorder());
        namePanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        deleteButton.setText(Configs.deleteContactText);
        editContactsToolTip.setText(Configs.editContactText);
        saveButton.setFont(Configs.font22);
        saveButton.setText(Configs.saveButtonText);
       btnBack.setText("");
       btnBack.setIcon(new ImageIcon(Configs.ICON_BACK));
       btnBack.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {        //Todo временное решение, убрать!!!
               super.mouseClicked(e);
               presenter.goToMainForm();
           }
       });
       editContactsToolTip.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {     //Todo временно!
               super.mouseClicked(e);
               presenter.goToMainForm();
           }
       });
       saveButton.addActionListener(e -> {
           presenter.editContact(numberField.getText(), nameField.getText(), surNameField.getText());
           presenter.goToMainForm();
       });
       deleteButton.addActionListener(e -> {
           presenter.deleteContact(person.getId());
           presenter.goToMainForm();
       });
   }

    private void createUIComponents() {
        rootPanel = this;
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
    }

    public void setEditUser(Person person){
        if(person != null){
            this.person = person;
            nameField.setText(person.getFirstName());
            surNameField.setText(person.getLastName());
            numberField.setText("+" + person.getPhone());
            this.avatar.getGraphics().drawImage(person.getPhotoSmall(), 0,0, avatar.getWidth(), avatar.getHeight(), null);
        }
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

    @Override
    public void setPresenter(IPresenter presenter){
       this.presenter = (EditContactPresenter) presenter;
    }

}
