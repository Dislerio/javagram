package diplomWork.viewInterface;

import javax.swing.*;

public interface viewInterface {
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
