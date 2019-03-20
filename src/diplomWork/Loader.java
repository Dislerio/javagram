package diplomWork;

import diplomWork.presenter.PhoneNumberPresenter;
import diplomWork.view.forms.MainFrame;

import javax.swing.*;

public class Loader {
    // public static JFrame frame;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            PhoneNumberPresenter phoneNumberPresenter = new PhoneNumberPresenter(frame);
            phoneNumberPresenter.runView();
        });


    }
}
