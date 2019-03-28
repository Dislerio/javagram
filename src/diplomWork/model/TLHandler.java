/**
 * Project Javagram Created by Shibkov Konstantin on 04.03.2019.
 */
package diplomWork.model;


import diplomWork.Configs;
import diplomWork.Log;
import diplomWork.model.objects.InputContact;
import diplomWork.model.objects.Person;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.Message;
import org.javagram.response.object.UserContact;
import org.telegram.api.TLAbsMessage;
import org.telegram.api.TLImportedContact;
import org.telegram.api.TLInputContact;
import org.telegram.api.TLInputPeerContact;
import org.telegram.api.contacts.TLImportedContacts;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.messages.TLAbsMessages;
import org.telegram.api.requests.TLRequestContactsImportContacts;
import org.telegram.api.requests.TLRequestMessagesGetHistory;
import org.telegram.tl.TLVector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Singleton Concurrency Pattern
 */

public class TLHandler {    //++

    private static volatile TLHandler instance;
    private static Logger l = Logger.getLogger("1");
    private TelegramApiBridge bridge;
    private String userPhone;
    private boolean isPhoneRegistered = false;
    private TelegramApi tlApi;
    private AuthAuthorization authorization;
    private String smsCodeChecked = "";

    String userFullName;
    String userFirstName;
    String userLastName;
    int userId;
    Image userPhotoSmall = null;
    Image getUserPhotoBig = null;

    private ArrayList<UserContact> contactList = new ArrayList<>();
    private ArrayList<Person> contactListCache = new ArrayList<>();

    public static TLHandler getInstance() {
        synchronized (TLHandler.class) {
            if (instance == null) {
                instance = new TLHandler();
            }
        }
        return instance;
    }

    private TLHandler() {
        //Подключаемся к API телеграмм
        try {
            bridge = new TelegramApiBridge(Configs.TL_SERVER, Configs.TL_APP_ID, Configs.TL_APP_HASH);
            //получаем доступ и ссылку на параметр api TelegramApiBridge
            getTlApiReflection();
            l.warning("tlApi.getState()" + tlApi.getState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearApiBridge() {
        userPhone = "";
        isPhoneRegistered = false;
        userFullName = "";
    }

    public void checkPhoneRegistered(String ph) {
        try {
            AuthCheckedPhone checkedPhone = bridge.authCheckPhone(ph);
            userPhone = ph;
            isPhoneRegistered = checkedPhone.isRegistered();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPhoneRegistered() {
        return isPhoneRegistered;
    }

    public void sendCode() throws IOException {
        if (isPhoneRegistered) {
            //Отправляем проверочный код для пользователя с введеным номером телефона
            AuthSentCode sentCode = bridge.authSendCode(userPhone);
        }
    }

    public void checkCode(String confirmCode) throws IOException {
        //проверка кода
        smsCodeChecked = confirmCode;
        authorization = bridge.authSignIn(confirmCode);
        //получаем имя, фамилию юзера и записываем
        userFullName = authorization.getUser().toString();
    }

    public void signUp(String smsCode, String firstName, String lastName) throws IOException {
        AuthAuthorization auth = bridge.authSignUp(smsCode, firstName, lastName);
    }

    public void signIn(String confirmCode) throws IOException {
        //проверка кода
        smsCodeChecked = confirmCode;
        authorization = bridge.authSignIn(confirmCode);
        //получаем имя, фамилию юзера и записываем
        userFirstName = authorization.getUser().getFirstName();
        userLastName = authorization.getUser().getLastName();
        userFullName = userFirstName + " " + userLastName;
        userId = authorization.getUser().getId();
    }

    public int getUserId() {
        return authorization.getUser().getId();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserNameFull() {
        return userFullName;
    }

    public Image getUserPhoto() {
        if (userPhotoSmall == null) {
            try {
                byte[] userPhoto = authorization.getUser().getPhoto(true);
                //if user has no photo - set default gag
                if (userPhoto != null) {
                    userPhotoSmall = ImageIO.read(new ByteArrayInputStream(userPhoto));
                } else {
                    File filePhotoSmall = new File(Configs.PATH_USER_PHOTO + "_user-small.jpg");
                    if (filePhotoSmall.exists() && filePhotoSmall.isFile()) {
                        try {
                            userPhotoSmall = ImageIO.read(filePhotoSmall);
                        } catch (IOException e) {
                            e.printStackTrace();
                            l.warning("ошибка чтения фото профиля с диска!");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                l.warning("НЕ ЗАГРУЗИЛАСЬ ФОТО ПОЛЬЗОВАТЕЛЯ!");
            } catch (NullPointerException e) {
                e.printStackTrace();
                l.warning("Ошибка загрузки изображения!");
            }
        }
        return userPhotoSmall;
    }

    public ArrayList<Person> getContacts() throws IOException {
        return getContactList(false);
    }

    public ArrayList<Person> getContactsForceUpdate() throws IOException {
        return getContactList(true);
    }

    public synchronized boolean editUserProfile(BufferedImage newPhoto, String firstName,
                                                String lastName) {
        try {
            bridge.accountUpdateProfile(firstName, lastName);
            if(newPhoto != null) updateUserPhoto(newPhoto);
            this.userFirstName = firstName;
            this.userLastName = lastName;
            userPhotoSmall = newPhoto;


        } catch (IOException e) {
            e.printStackTrace();
            Log.warning("TLHandler editUserProfile - IOException");
            return false;
        }
        return true;
    }

    public synchronized BufferedImage getContactPhotoSmall(Person contact) {
        BufferedImage photoSmall = null;
        if (contactListCache.contains(contact)) {
            //get native UserContact for use API methods
            UserContact tlContact = contact.getTLUserContact();
            //try to find img in cache folder with user photos
            File filePhotoSmall = new File(Configs.PATH_USER_PHOTO + tlContact.getId() + "-small.jpg");
            if (filePhotoSmall.exists() && filePhotoSmall.isFile()) {
                try {
                    photoSmall = ImageIO.read(filePhotoSmall);
                    return photoSmall;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //get contact from Telegram API
            try {
                photoSmall = ImageIO.read(new ByteArrayInputStream(tlContact.getPhoto(true)));
                //write cache
                File outputFile = new File(Configs.PATH_USER_PHOTO + contact.getId() + "-small.jpg");
                try {
                    ImageIO.write(photoSmall, "jpg", outputFile);
                    Log.info("file saved!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (NullPointerException e) {
                Log.warning(
                        "NullPointerException Repository getContactPhotoSmall()" + contact.getFullName());
                return null;
            }
            //after every request do sleep more than 1 second or you can get ban for 12-24 hours (FLOOD_WAIT)
            finally {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return photoSmall;
    }

    private synchronized ArrayList<Person> getContactList(boolean force) throws IOException {
        if (contactList.isEmpty() || contactListCache.isEmpty() || force){
            contactList = bridge.contactsGetContacts();
            contactListCache.clear();
            for (UserContact user : contactList) {
                contactListCache.add(new Person(user));
            }
        }
        return contactListCache;
    }

    public ArrayList<UserContact> getContactsTemp() throws IOException {        //Todo удалить, временный метод!
        ArrayList<UserContact> userContacts;
        userContacts = bridge.contactsGetContacts();
        return userContacts;
    }

    public void logOut() {
        try {
            if (authorization != null && bridge != null) {
                bridge.authLogOut();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 /*
 public static void getCurrentUser(){

    TLInputContact contact = new TLInputContact(0,"9996624443", "1", "1");
    TLVector<TLInputContact> v = new TLVector();
    v.add(contact);
    TLRequestContactsImportContacts ci = new TLRequestContactsImportContacts(v, true);
    TLImportedContacts ic = new TLImportedContacts();
    try {
      ic = (TLImportedContacts) tlApi.doRpcCall(ci);
    } catch (IOException e) {
      e.printStackTrace();
    }

    TLVector<TLImportedContact> listIC = ic.getImported();

    System.out.println(listIC.isEmpty());
  }
  */


    //  получаем доступ к приватной переменной api из TelegramApiBridge
    private void getTlApiReflection() {
        Field fieldApi;
        try {
            //попытка получить параметр api класса TelegramApiBridge
            fieldApi = bridge.getClass().getDeclaredField("api");
            //позволяем получить доступ к полю для работы с ним в обход private
            fieldApi.setAccessible(true);
            //передаем ссылку на приватный параметр api в текущий параметр tlApi
            tlApi = (TelegramApi) fieldApi.get(bridge);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Message> messagesGetHistoryByUserId(int userId) throws IOException {
        ArrayList<Message> messages = null;     //Todo убрать фиксированный лимит сообщений
        messages = messagesGetHistory(userId, 0, Integer.MAX_VALUE, 50);
        return messages;
    }

    public ArrayList<Message> messagesGetHistory(int userId, int offset, int maxId, int limit) throws IOException {
        TLRequestMessagesGetHistory request = new TLRequestMessagesGetHistory(new TLInputPeerContact(userId), offset, maxId, limit);
        TLVector<TLAbsMessage> tlAbsMessages = ((TLAbsMessages) tlApi.doRpcCall(request)).getMessages();
        ArrayList<Message> messages = new ArrayList();
        Iterator var7 = tlAbsMessages.iterator();

        while (var7.hasNext()) {
            TLAbsMessage tlMessage = (TLAbsMessage) var7.next();
            messages.add(new Message(tlMessage));
        }
        return messages;
    }

    public String getSmsCodeChecked() {
        return smsCodeChecked;
    }

    public int addContact(String phone, String firstname, String lastName) throws IOException {

        //check if contact already exist
        for (UserContact userContact : contactList) {
            if (userContact.getPhone().equals(phone)) {
                return -1;
            }
        }

        InputContact contact = new InputContact(0, phone, firstname, lastName);
        TLVector<TLInputContact> v = new TLVector();
        v.add(contact.createTLInputContact());
        TLRequestContactsImportContacts ci = new TLRequestContactsImportContacts(v, false);
        TLImportedContacts ic = this.tlApi.doRpcCall(ci);
        Log.info(ic.getUsers().size() + " ic.getUsers()");

        TLVector<TLImportedContact> listIC = ic.getImported();
        for (TLImportedContact c : listIC) {
            Log.info("TLImportedContact" + c.getUserId());
        }
        System.out.println(listIC.isEmpty());
        return listIC.size();
    }

    public void updateUserPhoto(BufferedImage photo) throws IOException {
        File outputFile = new File(Configs.PATH_USER_PHOTO + "_user-small.jpg");
        ImageIO.write(photo, "jpg", outputFile);
    }


}
