package diplomWork.presenter;

import diplomWork.view.forms.AddContactsForm;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.IView;

import java.io.IOException;

public class AddContactPresenter implements IPresenter {
    AddContactsForm view;
    public static AddContactPresenter presenter;
    private String phone, firstName, lastName;

    public synchronized static AddContactPresenter getPresenter(IView iView) {
        if (presenter == null) presenter = new AddContactPresenter(iView);
        presenter.frame.changeOverlayPanel(presenter.view.getRootPanel());
        return presenter;
    }

    private AddContactPresenter(IView iView) {
        this.view = (AddContactsForm) iView;
    }

    public void addContact(String phone, String firstName, String lastName) {
        this.phone = phone.trim().replaceAll("[^0-9]", "");
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();

        if (isContactFieldsValid()) {
            view.showInfo("Поиск и добавление контакта...");
            try {
                int numberAddedContacts = repository.updateContact(this.phone, this.firstName, this.lastName);
                if (numberAddedContacts == 1) {
                    view.showInfo("Контакт успешно добавлен");
                } else if (numberAddedContacts == -1) {
                    view.showError("Пользователь с таким именем уже существует!");
                } else if (numberAddedContacts == 0) {
                    view.showError("UserNotFound");
                } else {
                    view.showError("Неизвестная ошибка!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                view.showError("Ошибка ответа от сервера Telegram!");
            }
        }
    }

    @Override
    public void goToMainForm() {
        frame.changeOverlayPanel(null);
        frame.showFloatButton();
        view.clearError();
        ChatForm.getInstance().getContactsForce();
    }

    private boolean isContactFieldsValid() {
        // check phone
        if (phone.equals("")) {
            view.showError("PhoneEmpty");
            return false;
        }
        //TODO 11 set to Configs.TL_REQUIRED_PHONE_LENGTH
        if (phone.length() != 11) {
            view.showError("PhoneFormat");
            return false;
        }

        //check names
        if (firstName.equals("") || lastName.equals("")) {
            view.showError("EmptyFirstLast");
            return false;
        }
        return true;
    }


}
