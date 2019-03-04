package diplomWork.tests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FakeContact extends JLabel{
    Icon avatar;
    String name;
    String date = "12.12.12";
    String lastMessage = null;
    static Image maskGray = null;

    static {
        try {
            maskGray = ImageIO.read(new File("img/mask-gray.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Icon getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public FakeContact(Icon avatar, String name) {
        this.avatar = avatar;
        this.name = name;
        this.lastMessage = "Cообщений нет";
    }


    public static String setTextFormat(String name, String lastMessage){
        if(lastMessage.length() < 14){
            return ("<html><h2>" + name + "</h2><h5>" + lastMessage + "</h5></html>");
        } else {
            return ("<html><h2>" + name + "</h2><h5>" + lastMessage.substring(0, 11) + "...</h5></html>");
        }

    }
}
