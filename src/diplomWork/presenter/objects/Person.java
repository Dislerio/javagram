package diplomWork.presenter.objects;

import diplomWork.Configs;
import org.javagram.response.object.UserContact;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Person {
    private UserContact userContact;

    public Person(UserContact userContact) {
        this.userContact = userContact;

    }
    public boolean isOnline(){
        return userContact.isOnline();
    }

    public int getId() {
        return userContact.getId();
    }

    public String getFirstName() {
        return userContact.getFirstName();
    }

    public String getLastName() {
        return userContact.getLastName();
    }

    public String getPhone() {
        return userContact.getPhone();
    }

    public long getAccessHash() {
        return userContact.getAccessHash();
    }

    public BufferedImage getPhoto(boolean small) {
        BufferedImage img = Configs.IMG_DEFAULT_USER_PHOTO_41_41;
        try {
            BufferedImage imgApi = ImageIO.read(new ByteArrayInputStream(userContact.getPhoto(small)));
            if (imgApi != null) {
                Image i = imgApi.getScaledInstance(41, 41, Image.SCALE_SMOOTH);
                img = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = img.createGraphics();
                bGr.drawImage(i, 0, 0, null);
                bGr.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("      -------     NullPointerException");
        }
        return img;
    }

    private BufferedImage getUserPhoto(UserContact user) {      //возможно и не нужно
        BufferedImage img = Configs.IMG_DEFAULT_USER_PHOTO_41_41;
        try {
            BufferedImage imgApi = ImageIO.read(new ByteArrayInputStream(user.getPhoto(true)));
            if (imgApi != null) {
                Image i = imgApi.getScaledInstance(41, 41, Image.SCALE_SMOOTH);
                img = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = img.createGraphics();
                bGr.drawImage(i, 0, 0, null);
                bGr.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("      -------     NullPointerException");
        }
        return img;
    }
}
