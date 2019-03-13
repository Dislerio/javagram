package diplomWork;

import diplomWork.presenter.PhoneNumberPresenter;
import diplomWork.view.forms.MainScreen;

import javax.swing.*;

public class Loader {
    // public static JFrame frame;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainScreen frame = new MainScreen();
            PhoneNumberPresenter phoneNumberPresenter = new PhoneNumberPresenter(frame);
            phoneNumberPresenter.runView();
        });


    }
}
