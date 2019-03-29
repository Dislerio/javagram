package diplomWork.presenter;

import diplomWork.model.TLHandler;
import diplomWork.model.objects.Person;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.EditContacts;
import diplomWork.view.forms.MainFrame;
import diplomWork.viewInterface.IView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class EditContactPresenter implements IPresenter{
    TLHandler repository = TLHandler.getInstance();
    MainFrame frame;
    private static EditContactPresenter presenter;
    private EditContacts view;

    public static EditContactPresenter getPresenter(IView iView) {
        if (presenter == null) presenter = new EditContactPresenter(iView);
        presenter.frame.setContentPane(presenter.view.getRootPanel());
        return presenter;
    }

    public EditContactPresenter(IView iView){
        this.frame = MainFrame.getInstance();
        this.view = (EditContacts)iView;
    }

    public void editContact(String phone, String name, String surname){
        try {
            repository.addContact(phone, name, surname);
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }
    }

    public void deleteContact(int userId){
        try {
            repository.deleteContact(userId);
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }
    }

    void showError(){

    }
    boolean validUser(){
        return true;
    }

    void goToMainForm(){
    }



}
