package diplomWork.presenter;

import diplomWork.model.TLHandler;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.EditContacts;
import diplomWork.view.forms.MainFrame;
import diplomWork.viewInterface.IView;

import java.io.IOException;

public class EditContactPresenter implements IPresenter{
    private static EditContactPresenter presenter;
    private EditContacts view;

    public synchronized static EditContactPresenter getPresenter(IView iView) {
        if (presenter == null) presenter = new EditContactPresenter(iView);
        frame.setContentPane(presenter.view.getRootPanel());
        return presenter;
    }

    public EditContactPresenter(IView iView){
        this.view = (EditContacts)iView;
    }

    public void editContact(String phone, String name, String surname){
        try {
            repository.updateContact(phone, name, surname);
            ChatForm.getInstance().getContactsForce();
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }
    }

    public void deleteContact(int userId){
        try {
            repository.deleteContact(userId);
            ChatForm.getInstance().getContactsForce();
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }
    }
}
