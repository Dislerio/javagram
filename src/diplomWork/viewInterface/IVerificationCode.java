package diplomWork.viewInterface;

import diplomWork.presenter.VerificationCodePresenter;

public interface IVerificationCode extends IView {
    void setPhoneNumber(String phoneNumber);
    void showInfo(String strError);

    void callViewSignUp();
}
