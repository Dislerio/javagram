package diplomWork;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Configs {
    public static final String TL_SERVER = "149.154.167.40:443";
    public static final Integer TL_APP_ID = 612669;
    public static final String TL_APP_HASH = "87ec4522ab7da2903206d4715eb4801a";

    public static BufferedImage LOGO, LOGO_MINI, LOGO_MICRO, ICON_EDIT, ICON_TRASH, BG_IMAGE, ICON_PHONE,
            ICON_LOCK, ICON_SETTINGS, NO_AVATAR, MASK_BLUE_MINI, MASK_GRAY, IMG_BUTTON_SEND, IMG_BUTTON_SEARCH, ICON_PLUS, IMG_DEFAULT_USER;
    public static BufferedImage tavatar;
    public static String phoneNumberTooltipText, verificationCodeTooltipText, addContactsText,
            addContactsToolTip, editContactText, deleteContactText, saveButtonText;
    public static String INTERFACE_PHONE_MASK;
    public static char INTERFACE_PHONE_MASK_PLACEHOLDER;
    public static Font font16, font18, font22, font25, font32, font40;
    static{
        //Fonts
        font16 = getFont(16);
        font18 = getFont(18);
        font22 = getFont(22);
        font25 = getFont(25);
        font32 = getFont(32);
        font40 = getFont(40);
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
        IMG_DEFAULT_USER = readImage("img/icon-default-user.png");
        //Configs data
        INTERFACE_PHONE_MASK = "+# (###) #######";
        INTERFACE_PHONE_MASK_PLACEHOLDER = '_';
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
