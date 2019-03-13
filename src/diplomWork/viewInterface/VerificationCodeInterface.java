package diplomWork.viewInterface;

import diplomWork.presenter.VerificationCodePresenter;

public interface VerificationCodeInterface extends viewInterface {
    boolean isCodeValid(String code);
    void setPresenter(VerificationCodePresenter presenter);
}
