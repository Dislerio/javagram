package diplomWork.presenter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import diplomWork.Configs;
import diplomWork.Log;
import diplomWork.model.TLHandler;
import diplomWork.model.objects.Person;
import diplomWork.view.components.ChatPanel;
import diplomWork.view.components.ContactPanel;
import diplomWork.view.forms.*;
import diplomWork.viewInterface.IView;
import org.javagram.response.object.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ChatFormPresenter implements IPresenter{       //+ +/-
    private volatile ArrayList<Person> contactList = new ArrayList<>();
    private volatile DefaultListModel<Person> contactsListModel = new DefaultListModel<>();
    TLHandler repository = TLHandler.getInstance();
    private MainFrame frame;
    private ChatForm view;
    private static ChatFormPresenter presenter;

    public static ChatFormPresenter getPresenter(IView iView){
        if(presenter == null){
            presenter = new ChatFormPresenter(iView);
        }
        presenter.frame.setContentPane(presenter.view.getRootPanel());
        return presenter;
    }

    private ChatFormPresenter(IView iView){
        this.frame = MainFrame.getInstance();
        view = (ChatForm)iView;
        view.setSelfName(TLHandler.getInstance().getUserNameFull());
        view.setSelfUserPhoto(TLHandler.getInstance().getUserPhoto());
        view.showInfo(String.valueOf(TLHandler.getInstance().getUserId()));
        frame.setContentPane(view.getRootPanel());
        //getContactList();
        frame.addWindowListener(new WindowAdapter() {
        });
    }

    public synchronized void getContactList(){

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //get from Telegram Api
                    contactList = repository.getContacts();
                    Log.info("contactList.size() = " + contactList.size());
                    //clear
                    contactsListModel.clear();
                    for (Person contact : contactList) {
                        Log.info("add contact " + contact.getId() + ":" + contact.getFullName());
                        contactsListModel.addElement(contact);
                    }

                    view.setContactList(contactsListModel);

                } catch (Exception e) {
                    e.printStackTrace();
                    view.showError("Ошибка при получении списка контактов! IOException getContactList()");
                }
            }

        });
        th.start();
    }

    public synchronized void refreshUserPhotos() {
        //wait for Moon Phase
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // and go...
        Thread threadGetPhotos = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < contactsListModel.getSize(); i++) {
                    Person c = contactsListModel.get(i);
                    Log.info("start set small photo to contact " + c.getFullName() + "(" + c.getId() + ")");
                    BufferedImage photoSmall = repository.getContactPhotoSmall(c);
                    if (photoSmall != null) {
                        c.setPhotoSmall(photoSmall);
                        Log.info("small photo have setted for " + c.getFullName());
                    }
                    view.repaintContactList();
                }
            }
        }
        );
        threadGetPhotos.start();
    }

    public synchronized void getChat(int userId){
        ArrayList<ChatPanel> panels = new ArrayList<>();
        Thread thread = new Thread(() ->{           //Todo сделать сначала подгрузку списка, после - обновление аватарок
            try{
                ArrayList<Message> messages = TLHandler.getInstance().messagesGetHistoryByUserId(userId);
                view.showInfo("Сообщений получено - " + messages.size());
                for(Message m : messages){
                    panels.add(new ChatPanel(m));
                }
            } catch (IOException e) {
                view.showError("Ошибка получения списка контактов IOException getChat()");
                e.printStackTrace();
            }
            view.setChatList(panels);
        });
        thread.start();
    }

    public void callAddPresenter(){
        AddContactsForm.getInstance();
    }

    public void callEditPresenter(Person person){
        EditContacts.getInstance(person);
    }
    public void callSettingsPresenter(){
        ProfileSettings.getInstance();
    }

    public void sendMessage(String text, Person personTo) {
        repository.sendMessage(text, personTo.getId());
    }
}
