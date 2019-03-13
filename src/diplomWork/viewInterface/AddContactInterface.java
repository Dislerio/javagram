package diplomWork.viewInterface;

import diplomWork.presenter.AddContactPresenter;

import javax.swing.*;

public interface AddContactInterface {
    void setPresenter(AddContactPresenter af);
    JPanel getRootPanel();
}
