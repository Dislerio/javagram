package diplomWork;

import diplomWork.presenter.PhoneNumberPresenter;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.PhoneNumber;

import javax.swing.*;

public class Loader {
    public static MainFrame frame;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    PhoneNumber.getInstance();

//                    PhoneNumberPresenter phoneNumberPresenter = new PhoneNumberPresenter(frame);
//                    phoneNumberPresenter.runView();
                }
            });
        });


    }
}
