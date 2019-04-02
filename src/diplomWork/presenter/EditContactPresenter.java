package diplomWork.presenter;

import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.EditContacts;
import diplomWork.view.forms.IView;

import java.io.IOException;

public class EditContactPresenter implements IPresenter{
    private static EditContactPresenter presenter;
    private EditContacts view;

    public synchronized static EditContactPresenter getPresenter(IView iView) {
        if (presenter == null) presenter = new EditContactPresenter(iView);
        frame.changeOverlayPanel(presenter.view.getRootPanel());
        return presenter;
    }

    public EditContactPresenter(IView iView){
        this.view = (EditContacts)iView;
    }

    public void editContact(String phone, String name, String surname){
        try {
            repository.updateContact(phone, name, surname);
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

    @Override
    public void goToMainForm() {
        frame.changeOverlayPanel(null);
        frame.showFloatButton();
        ChatForm.getInstance().getContactsForce();
    }
}
