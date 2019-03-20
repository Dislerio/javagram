package diplomWork.presenter;

import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.ProfileSettings;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileSettingsPresenter implements IPresenter{
    MainFrame frame;


    public ProfileSettingsPresenter(MainFrame frame){
        this.frame = frame;
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
            ChatFormPresenter cp = ChatFormPresenter.getPresenter(frame);
            cp.runView("Что-то там");
    }
}
