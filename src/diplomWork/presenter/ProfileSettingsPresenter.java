package diplomWork.presenter;

import diplomWork.view.forms.MainScreen;
import diplomWork.view.forms.ProfileSettings;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileSettingsPresenter {
    MainScreen frame;


    public ProfileSettingsPresenter(MainScreen frame){
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
