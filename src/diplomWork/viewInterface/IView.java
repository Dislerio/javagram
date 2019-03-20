package diplomWork.viewInterface;

import diplomWork.presenter.IPresenter;

import javax.swing.*;

public interface IView {
    void setPresenter(IPresenter af);

    //show various error
    void showError(String strError);

    void clearError();

    //show Loading Icon (animated)
    void showLoadingProcess();

    //hide Loading Icon (animated)
    void hideLoadingProcess();

    //main panel to show in frame

    JPanel getRootPanel();
}
