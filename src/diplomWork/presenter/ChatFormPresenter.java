package diplomWork.presenter;

import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import diplomWork.Log;
import diplomWork.model.TLHandler;
import diplomWork.presenter.objects.Person;
import diplomWork.view.components.ChatPanel;
import diplomWork.view.forms.*;
import diplomWork.view.forms.IView;
import org.javagram.response.object.*;

import javax.swing.*;

public class ChatFormPresenter implements IPresenter{       //+ +/-
    private volatile ArrayList<Person> contactList = new ArrayList<>();
    private volatile DefaultListModel<Person> contactsListModel = new DefaultListModel<>();
    private ChatForm view;
    private static ChatFormPresenter presenter;
    private Thread upMessages;
    boolean upMessagesStopped = false;

    public synchronized static ChatFormPresenter getPresenter(IView iView){
        if(presenter == null){
            presenter = new ChatFormPresenter(iView);
        }
        presenter.switchIn();
        return presenter;
    }

    private ChatFormPresenter(IView iView){
        view = (ChatForm)iView;
        view.setSelfName(TLHandler.getInstance().getUserNameFull());
        view.setSelfUserPhoto(TLHandler.getInstance().getUserPhoto());
        view.showInfo(String.valueOf(TLHandler.getInstance().getUserId()));
        frame.setContentPane(view.getRootPanel());
        frame.addWindowListener(new WindowAdapter() {
        });
        getContactList(false);
        updateMessages();
    }

    public void getContactList(boolean force){

        Thread th = new Thread(() -> {
            try {
                //get from Telegram Api
                if(force)
                    contactList = repository.getContactsForceUpdate();
                else
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
                    try {       //пауза
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                getContactList(false);
            }
        });
        th.start();
        view.repaintContactList();
        if(force) refreshUserPhotos();
    }

    public synchronized void refreshUserPhotos() {
        //wait for Moon Phase
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // and go...
        Thread threadGetPhotos = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < contactsListModel.getSize(); i++) {
                    Person c = contactsListModel.get(i);
                    if(c.isNoFoto()) continue;
                    Log.info("start set small photo to contact " + c.getFullName() + "(" + c.getId() + ")");
                    BufferedImage photoSmall = repository.getContactPhotoSmall(c);
                    if (photoSmall != null) {
                        c.setPhotoSmall(photoSmall);
                        Log.info("small photo have setted for " + c.getFullName());
                    }
                }
                view.repaintContactList();
            }
        }
        );
        threadGetPhotos.start();
    }

    public synchronized void getChat(int userId){
        ArrayList<ChatPanel> panels = new ArrayList<>();
        Thread thread = new Thread(() ->{
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
        switchOut();
    }

    public void callEditPresenter(Person person){
        EditContacts.getInstance(person);
        switchOut();
    }

    public void callSettingsPresenter(){
        ProfileSettings.getInstance();
        switchOut();
    }

    private void switchOut(){
        frame.hideFloatButton();
        upMessagesStopped = true;
    }

    private void switchIn(){
        frame.showFloatButton();
        upMessagesStopped = false;
        frame.setContentPane(presenter.view.getRootPanel());
    }

    public void sendMessage(String text, Person personTo) {
        repository.sendMessage(text, personTo.getId());
    }

    private void updateMessages(){
        upMessages = new Thread(() ->{
            while (true){
                try{
                    Thread.sleep(5000);
                    if(upMessagesStopped) continue;
                    repository.updateLastMessages(contactList);
                    view.repaintContactList();
                } catch (Exception e) {
                    view.showError("Ошибка получения сообщений Exception updateMessages(): " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        upMessages.start();
    }

}
