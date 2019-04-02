package diplomWork.model.objects;

import diplomWork.Configs;
import org.javagram.response.object.UserContact;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private UserContact userContact;
    private String lastMessage;
    private int time;
    private BufferedImage photoSmall = null;
    private boolean noFoto = false;
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");      //Todo временное решение
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    private static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("dd.MM.yyyy");



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
            System.out.println(" Photo:     -------     NullPointerException");
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

    public String getFullName() {
        return  userContact.toString();
    }

    public String getTime() {       //Todo вынести в интерфейс
        String dateOfMessage;
        Date date = new Date((long)this.time * 1000);
        if(dateWithoutTime.format(date).equals(dateWithoutTime.format(new Date(System.currentTimeMillis())))){
            dateOfMessage = timeFormat.format(date);
        } else {
            dateOfMessage = dateFormat.format(date);
        }
        return dateOfMessage;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public UserContact getTLUserContact() {
        return userContact;
    }

    public BufferedImage getPhotoSmall() {
        if(photoSmall != null) return photoSmall;
        else return Configs.IMG_USER_PHOTO_EMPTY_160;
    }

    public void setPhotoSmall(BufferedImage photoSmall) {
        this.photoSmall = photoSmall;
    }

    public boolean isNoFoto() {
        return noFoto;
    }

    public void setNoFoto(boolean noFoto) {
        this.noFoto = noFoto;
    }
}
