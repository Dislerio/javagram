package diplomWork.view.forms;

import diplomWork.presenter.IPresenter;

import javax.swing.*;

public interface IView {
    MainFrame frame = MainFrame.getInstance();

    default void goBackToPhoneInput() {
        PhoneNumber.getInstance();
    }

    void setPresenter(IPresenter af);

    default void showInfo(String strError) {
    }

    default void showError(String strError) {
    }

    default void clearError() {
    }

    //show Loading Icon (animated)
    default void showLoadingProcess() {
    }

    //hide Loading Icon (animated)
    default void hideLoadingProcess() {
    }

    //main panel to show in frame
    JPanel getRootPanel();

    default void goToMainForm() {
        ChatForm.getInstance();
    }

    default String formatPhoneNumber(String phone) {
        return "+" + phone.substring(0, 1) + " (" + phone.substring(1, 4) + ") " + phone.substring(4);
    }
}
