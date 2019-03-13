package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.EditContactPresenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditContacts {
    private JPanel rootPanel;
    private JPanel dataPanel;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JPanel avatarPanel;
    private JPanel namePanel;
    private JButton saveButton;
    private JLabel editContactsToolTip;
    private JLabel numberField;
    BufferedImage logo, trashIcon, phoneLogoImage;
    EditContactPresenter presenter;

   public EditContacts(){       //отработано

            trashIcon = Configs.ICON_TRASH;

        deleteButton.setIcon(new ImageIcon(trashIcon));
        nameTextField.setBorder(BorderFactory.createEmptyBorder());
        namePanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        deleteButton.setText(Configs.deleteContactText);
        editContactsToolTip.setText(Configs.editContactText);
        saveButton.setFont(Configs.font22);
        saveButton.setText(Configs.saveButtonText);



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

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setPresenter(EditContactPresenter presenter){
       this.presenter = presenter;
    }
    public void setEditUser(String user, String number){       //TO DO     временно
       nameTextField.setText(user);
       numberField.setText(number);
    }

    public Component getActionSave() {
        return saveButton;
    }
}
