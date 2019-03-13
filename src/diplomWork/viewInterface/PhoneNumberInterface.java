package diplomWork.viewInterface;

import diplomWork.presenter.PhoneNumberPresenter;

public interface PhoneNumberInterface extends viewInterface{

    void showPhoneFormatError(String strError);
    void setPresenter(PhoneNumberPresenter presenter);

}
