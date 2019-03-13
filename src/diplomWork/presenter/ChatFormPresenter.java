package diplomWork.presenter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import diplomWork.view.forms.MainForm;
import diplomWork.view.forms.MainScreen;
import org.javagram.response.object.*;

public class ChatFormPresenter {
    Map contactList;
    User user;
    Map messages;
    String userPhoneTemp;   //временная пременная
    MainScreen frame;
    MainForm mf;
    private static ChatFormPresenter presenter;

    public static ChatFormPresenter getPresenter(MainScreen frame){
        if(presenter == null){
            presenter = new ChatFormPresenter(frame);
        }
        return presenter;
    }

    private ChatFormPresenter(MainScreen frame){
        this.frame = frame;
    }

    public void runView(String userPhone){
        this.userPhoneTemp = userPhone;
        mf = new MainForm();
        mf.setPresenter(this);
        frame.setContentPane(mf.getRootPanel());
        mf.getSelfNameLabel().setText(userPhone);

        mf.getActionAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                callAddPresenter();
            }
        });
        mf.getActionEdit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                callEditPresenter();
            }
        });
        mf.getActionSettings().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                callSettingsPresenter();
            }
        });
    }

    public void callAddPresenter(){
        AddContactPresenter acp = new AddContactPresenter(frame);
        acp.runView();

    }
    public void callEditPresenter(){
        EditContactPresenter ecp = new EditContactPresenter(frame);
        ecp.runView(mf.getChatWithName());

    }
    public void callSettingsPresenter(){
        ProfileSettingsPresenter psp = new ProfileSettingsPresenter(frame);
        psp.runView(mf.getSelfNameLabel().getText());
    }
}
