package diplomWork.presenter;

import diplomWork.model.TLHandler;
import diplomWork.view.forms.MainFrame;

public interface IPresenter {
    TLHandler repository = TLHandler.getInstance();
    MainFrame frame = MainFrame.getInstance();

    default String getPhoneNumber() {
        return repository.getUserPhone();
    }

    default void goBackToPhoneInput() {}

    default void goToMainForm(){}

    default boolean isPhoneValid(String phone) {
        return phone.matches("^\\+*[78][9]\\d{9}");
    }
}
