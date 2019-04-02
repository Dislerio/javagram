package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.Log;
import diplomWork.presenter.IPresenter;
import diplomWork.presenter.ProfileSettingsPresenter;
import diplomWork.view.components.TransparentBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ProfileSettings extends TransparentBackground implements IView {
    private BufferedImage logo;
    private BufferedImage editIcon;
    private BufferedImage deleteIcon;
    private BufferedImage avatar;
    private BufferedImage userPhotoNew;
    private JButton saveButton;
    private JLabel btnLogout;
    private JLabel deleteAvatar;
    private JLabel editAvatar;
    private JLabel errLabel;
    private JLabel numLabel;
    private JLabel title;
    private JPanel avPanel;
    private JPanel rootPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private ProfileSettingsPresenter presenter;
    private static ProfileSettings instance;

    public synchronized static ProfileSettings getInstance() {
        if (instance == null) instance = new ProfileSettings();
        instance.setPresenter(ProfileSettingsPresenter.getPresenter(instance));
        instance.presenter.getUserProfileData();
        return instance;
    }

    private ProfileSettings() {
        logo = Configs.LOGO_MINI;
        editIcon = Configs.ICON_EDIT;
        deleteIcon = Configs.ICON_TRASH;

        setPresenter(ProfileSettingsPresenter.getPresenter(this));

        nameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        surnameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        editAvatar.setIcon(new ImageIcon(editIcon));
        deleteAvatar.setIcon(new ImageIcon(deleteIcon));
        numLabel.setForeground(Color.RED);
        numLabel.setFont(Configs.getFont(14));
        btnLogout.setForeground(Color.RED);
        btnLogout.setFont(Configs.getFont(12));
        btnLogout.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        btnLogout.setText("Выйти");

        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.logOut();
            }
        });

        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        //Todo временное решение, убрать!!!
                super.mouseClicked(e);
                presenter.goToMainForm();
            }
        });

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.setNewUserData(userPhotoNew, nameTextField.getText(),
                        surnameTextField.getText());
                presenter.goToMainForm();
            }
        });
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = (ProfileSettingsPresenter) presenter;
    }


    public void fillUserProfileData(String... names) {
        nameTextField.setText(names[0]);
        surnameTextField.setText(names[1]);
        numLabel.setText("+" + names[2]);
    }

    public void fillUserPhoto(Image photo) {
        if (photo == null) {
            avatar = Configs.IMG_USER_PHOTO_EMPTY_160;
        } else {
            avatar = resizeAndCropImage(photo, 160, 160);
        }
        avPanel.getGraphics().drawImage(avatar, 0, 0, 160, 160, avPanel);     //Todo не работает

    }

    //CROP AND RESIZE

    private BufferedImage resizeAndCropImage(Image photo, int width, int height) {
        BufferedImage buff = convertImageToBufferImage(photo);
        buff = cropImageInSquare(buff);
        Image img = buff.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return convertImageToBufferImage(img);
    }

    private BufferedImage cropImageInSquare(BufferedImage photo) {
        return photo.getWidth() >= photo.getHeight() ? cropWidthLong(photo) : cropHeightLong(photo);
    }

    private BufferedImage cropHeightLong(BufferedImage photo) {
        int pointX = 0;
        int pointY = (photo.getHeight() - photo.getWidth()) / 2;
        Log.info("pointX=" + pointX + " pointY=" + pointY);
        return cropFromPoint(photo, pointX, pointY, photo.getWidth());
    }

    private BufferedImage cropWidthLong(BufferedImage photo) {
        int pointX = (photo.getWidth() - photo.getHeight()) / 2;
        int pointY = 0;
        Log.info("pointX=" + pointX + " pointY=" + pointY);
        return cropFromPoint(photo, pointX, pointY, photo.getHeight());
    }

    private BufferedImage cropFromPoint(BufferedImage photo, int pointX, int pointY, int sideLenght) {
        BufferedImage img = photo
                .getSubimage(pointX, pointY, sideLenght,
                        sideLenght); //fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }

    private BufferedImage convertImageToBufferImage(Image img) {
        BufferedImage buff = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = buff.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return buff;
    }

    private void createUIComponents() {
        rootPanel = this;
    }
}
