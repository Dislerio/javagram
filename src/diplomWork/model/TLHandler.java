/**
 * Project Javagram Created by Shibkov Konstantin on 04.03.2019.
 */
package diplomWork.model;


import diplomWork.Configs;
import diplomWork.model.objects.InputContact;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.Dialog;
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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Singleton Concurrency Pattern
 */

public class TLHandler {    //++

  private static volatile TLHandler instance;
  private static Logger l = Logger.getLogger("1");
  private TelegramApiBridge bridge;
  private String userPhone;
  private boolean isPhoneRegistered = false;
  private String userNameFull;
  private TelegramApi tlApi;
  private AuthAuthorization authorization;
  private String smsCodeChecked = "";

  String userFullName;
  String userFirstName;
  String userLastName;
  int userId;
  private ArrayList<UserContact> contactList = new ArrayList<>();

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
    userNameFull = "";
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
    authorization = bridge.authSignIn(confirmCode);
    //получаем имя, фамилию юзера и записываем
    userNameFull = authorization.getUser().toString();
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

  public String getUserNameFull() {
    return userNameFull;
  }

  public Image getUserPhoto() {
    Image img = Configs.IMG_DEFAULT_USER;
    try {
      byte[] userPhoto = authorization.getUser().getPhoto(true);
      if (userPhoto != null) {
        img = ImageIO.read(new ByteArrayInputStream(userPhoto));
      }
    } catch (IOException e) {
      e.printStackTrace();
      l.warning("НЕ ЗАГРУЗИЛАСЬ ФОТО ПОЛЬЗОВАТЕЛЯ!");
    }
    return img;
  }

  public ArrayList<UserContact> getContacts() throws IOException {
    ArrayList<UserContact> userContacts;
    userContacts = bridge.contactsGetContacts();
    return userContacts;
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
      messages = messagesGetHistory(userId, 0,Integer.MAX_VALUE, 50);
    return messages;
  }

  public ArrayList<Message> messagesGetHistory(int userId, int offset, int maxId, int limit) throws IOException {
    TLRequestMessagesGetHistory request = new TLRequestMessagesGetHistory(new TLInputPeerContact(userId), offset, maxId, limit);
    TLVector<TLAbsMessage> tlAbsMessages = ((TLAbsMessages)tlApi.doRpcCall(request)).getMessages();
    ArrayList<Message> messages = new ArrayList();
    Iterator var7 = tlAbsMessages.iterator();

    while(var7.hasNext()) {
      TLAbsMessage tlMessage = (TLAbsMessage)var7.next();
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
    l.info(ic.getUsers().size() + " ic.getUsers()");

    TLVector<TLImportedContact> listIC = ic.getImported();
    for (TLImportedContact c : listIC){
      l.info("TLImportedContact" + c.getUserId());
    }
    System.out.println(listIC.isEmpty());
    return listIC.size();
  }
}
