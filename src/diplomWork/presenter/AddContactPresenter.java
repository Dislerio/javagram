package diplomWork.presenter;

import diplomWork.view.forms.AddContactsForm;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.MainFrame;
import diplomWork.viewInterface.IView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddContactPresenter implements IPresenter{
    MainFrame frame;
    AddContactsForm view;
    public static AddContactPresenter presenter;

    public static AddContactPresenter getPresenter(IView iView){
        if(presenter == null) presenter = new AddContactPresenter(iView);
        return presenter;
    }

    private AddContactPresenter(IView iView){
        this.view = (AddContactsForm) iView;
        this.frame = MainFrame.getInstance();
        frame.setContentPane(view.getRootPanel());

    }

    void addContact(){
        //Todo
    }
    void showError(){
        //Todo
    }

    public void goToMainForm(){
        frame.setContentPane(ChatForm.getInstance().getRootPanel());
    }





}
