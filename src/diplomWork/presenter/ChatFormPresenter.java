package diplomWork.presenter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import diplomWork.Configs;
import diplomWork.model.TLHandler;
import diplomWork.tests.FakeChat;
import diplomWork.view.components.ChatPanel;
import diplomWork.view.components.ContactPanel;
import diplomWork.view.forms.AddContactsForm;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.ProfileSettings;
import diplomWork.viewInterface.IView;
import org.javagram.response.object.*;

import javax.imageio.ImageIO;

public class ChatFormPresenter implements IPresenter{       //+ +/-
//    Map contactList;
//    User user;
//    Map messages;
    String userPhoneTemp;   //временная пременная
    private MainFrame frame;
    private ChatForm view;
    private static ChatFormPresenter presenter;

    public static ChatFormPresenter getPresenter(IView iView){
        if(presenter == null){
            presenter = new ChatFormPresenter(iView);
        }
        return presenter;
    }

    private ChatFormPresenter(IView iView){
        this.frame = MainFrame.getInstance();
        view = (ChatForm)iView;
        view.setSelfName(TLHandler.getInstance().getUserNameFull());
        view.setSelfUserPhoto(TLHandler.getInstance().getUserPhoto());
        view.showInfo(String.valueOf(TLHandler.getInstance().getUserId()));
//        view.setChatList();
        frame.setContentPane(view.getRootPanel());
        getContactList();
    }

    public synchronized void getContactList(){
        ArrayList<ContactPanel> panels = new ArrayList<>();

        Thread thread = new Thread(() ->{           //Todo сделать сначала подгрузку списка, после - обновление аватарок
            try{
                ArrayList<UserContact> userContacts = TLHandler.getInstance().getContacts();
                for(UserContact uc : userContacts){
                    Thread.sleep(150);
                    view.showInfo("Получаю контакт № " + panels.size());
                    ContactPanel panel = new ContactPanel(getUserPhoto(uc),
                            uc.toString(), null, null, uc.getId());
                    panels.add(panel);
                    //i++;
                }
            } catch (IOException e){
                view.showError("Ошибка получения списка контактов IOException");
                e.printStackTrace();
            } catch (InterruptedException e){e.printStackTrace();}
            view.showInfo("Контактов получено: " + panels.size());
            view.setContactList(panels);
        });
        thread.start();
        //return FakeContacts.getContactPanels();     //Todo
    }

    public BufferedImage getUserPhoto(UserContact user) {
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
        // Create a buffered image with transparency
        return img;
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
                view.showError("Ошибка получения списка контактов IOException");
                e.printStackTrace();
            }
            view.setChatList(panels);
        });
        thread.start();
        //FakeChat.getChatPanels();            //Todo
    }

    public void callAddPresenter(){
        AddContactsForm acf= AddContactsForm.getInstance();
        acf.setPresenter(AddContactPresenter.getPresenter(acf));
    }
    public void callEditPresenter(){
        EditContactPresenter ecp = new EditContactPresenter(frame);
        ecp.runView(view.getChatWithName());

    }
    public void callSettingsPresenter(){
        ProfileSettings psf = new ProfileSettings();
        psf.setPresenter(ProfileSettingsPresenter.getPresenter(psf));
    }
}
