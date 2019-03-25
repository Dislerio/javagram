package diplomWork.presenter;

import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.ProfileSettings;
import diplomWork.viewInterface.IView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileSettingsPresenter implements IPresenter{
    MainFrame frame;
    ProfileSettings view;
    public static ProfileSettingsPresenter presenter;

    public static ProfileSettingsPresenter getPresenter(IView iView){
        if(presenter == null){
            presenter = new ProfileSettingsPresenter(iView);
        }
        return presenter;
    }

    private ProfileSettingsPresenter(IView iView){
        this.frame = MainFrame.getInstance();
        this.view = (ProfileSettings)iView;
    }

    public void runView(String user){
        ProfileSettings ps = new ProfileSettings();
        ps.setPresenter(this);
        frame.setContentPane(ps.getRootPanel());
        ps.getSaveButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goToMainForm();
            }
        });
    }

    private void goToMainForm(){
        frame.setContentPane(ChatForm.getInstance().getRootPanel());

    }
}
