package diplomWork.viewInterface;

import diplomWork.presenter.VerificationCodePresenter;

public interface IVerificationCode extends IView {
    boolean isCodeValid(String code);
}
