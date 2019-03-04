package diplomWork.tests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FakeContacts {
    static String[] contactsSample = {"Маша" , "Катя" , "Марина" , "Ангелина" , "Ольга" , "Настя" , "Таня" , "Света" , "Женя"};
    static FakeContact[] contacts = new FakeContact[9];
    static ContactPanelTest[] contactPanels = new ContactPanelTest[9];



    static {

        File[] avatars = new File("img/avatars").listFiles();
        try {
            for(int i = 0; i < avatars.length; i++){
                contacts[i] = (new FakeContact(new ImageIcon(ImageIO.read(avatars[i])), contactsSample[i]));
                }
            contacts[0].lastMessage = "Что-то написано было";
            contacts[1].lastMessage = "Вы: Привет! Как дела дорогая?";
            // третий способ
            for(int i = 0; i < 9; i++){
                contactPanels[i] = new ContactPanelTest(contacts[i].getAvatar(),contacts[i].getName(), contacts[i].date, contacts[i].lastMessage);
            }

            } catch (IOException e) {
                e.printStackTrace();
        }



    }

    public static String[] getContactsSample() {
        return contactsSample;
    }

    public static FakeContact[] getContacts() {
        return contacts;
    }

    public static ContactPanelTest[] getContactPanels() {
        return contactPanels;
    }
}
