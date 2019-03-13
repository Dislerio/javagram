package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.presenter.ProfileSettingsPresenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileSettings {      //отработано
    private BufferedImage logo;
    private BufferedImage editIcon;
    private BufferedImage deleteIcon;
    private JPanel rootPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JButton saveButton;
    private JLabel editAvatar;
    private JLabel deleteAvatar;
    private ProfileSettingsPresenter presenter;

    public ProfileSettings(){
            logo = Configs.LOGO_MINI;
            editIcon = Configs.ICON_EDIT;
            deleteIcon = Configs.ICON_TRASH;

    nameTextField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.white));
    surnameTextField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.white));
    editAvatar.setIcon(new ImageIcon(editIcon));
    deleteAvatar.setIcon(new ImageIcon(deleteIcon));
}
    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Component getSaveButton() {            //TO DO
        return saveButton;
    }

    public void setPresenter(ProfileSettingsPresenter presenter) {
        this.presenter = presenter;
    }
}
