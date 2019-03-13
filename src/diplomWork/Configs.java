package diplomWork;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Configs {
    public static BufferedImage LOGO, LOGO_MINI, LOGO_MICRO, ICON_EDIT, ICON_TRASH, BG_IMAGE, ICON_PHONE,
            ICON_LOCK, ICON_SETTINGS, NO_AVATAR, MASK_BLUE_MINI, MASK_GRAY, IMG_BUTTON_SEND, IMG_BUTTON_SEARCH, ICON_PLUS;
    public static BufferedImage tavatar;
    public static String phoneNumberTooltipText, verificationCodeTooltipText, addContactsText,
            addContactsToolTip, editContactText, deleteContactText, saveButtonText;
    public static Font font16, font18, font22, font25, font32, font40;
    static{
        //Fonts
        font16 = new Font("Open Sans",Font.BOLD, 16);
        font18 = new Font("Open Sans",Font.BOLD, 18);
        font22 = new Font("Open Sans",Font.BOLD, 22);
        font25 = new Font("Open Sans",Font.BOLD, 25);
        font32 = new Font("Open Sans",Font.BOLD, 32);
        font40 = new Font("Open Sans",Font.BOLD, 40);
        //Strings
        phoneNumberTooltipText = "<html><center>Введите код страны и номер<br>вашего мобильного телефона<center></html>";
        verificationCodeTooltipText = "<html><center>Введите код страны и номер<br>вашего мобильного телефона<center></html>";
        addContactsToolTip = "<html><center>Введите код страны и номер<br>мобильного телефона пользовтеля</center></html>";
        addContactsText = "Добавление контакта";
        editContactText = "Редактирование контакта";
        deleteContactText = "Удалить пользователя";
        saveButtonText = "Сохранить";

        //Images
        LOGO = readImage("img/logo.png");
        LOGO_MINI = readImage("img/logo-mini.png");
        LOGO_MICRO = readImage("img/logo-micro.png");
        BG_IMAGE = readImage("img/background.png");
        ICON_EDIT = readImage("img/icon-edit.png");
        ICON_PHONE = readImage("img/icon-phone.png");
        ICON_SETTINGS = readImage("img/icon-settings.png");
        ICON_PLUS = readImage("img/icon-plus.png");
        ICON_TRASH = readImage("img/icon-trash.png");
        ICON_LOCK = readImage("img/icon-lock.png");
        NO_AVATAR = readImage("img/placeholder_no_avatar.png");
        MASK_BLUE_MINI = readImage("img/mask-blue-mini.png");
        MASK_GRAY = readImage("img/mask-gray.png");
        IMG_BUTTON_SEND = readImage("img/button-send.png");
        IMG_BUTTON_SEARCH = readImage("img/icon-search.png");
        //test avatar
        tavatar = readImage("img/tavatar.jpg");


    }
    private static BufferedImage readImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Font getFont(int size){
        return new Font("Open Sans",Font.BOLD, size);
    }
}
