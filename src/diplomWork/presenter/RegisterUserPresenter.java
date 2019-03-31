package diplomWork.presenter;

import diplomWork.Log;
import diplomWork.model.TLHandler;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.RegForm;
import diplomWork.viewInterface.IView;

import java.io.IOException;

public class RegisterUserPresenter implements IPresenter{
    private RegForm view;
    private static RegisterUserPresenter instance;

    public synchronized static RegisterUserPresenter getPresenter(IView iView) {
        if(instance ==null){
            instance = new RegisterUserPresenter(iView);
        }
        frame.setContentPane(instance.view.getRootPanel());
        return instance;
    }

    private RegisterUserPresenter(IView iView) {
        this.view = (RegForm) iView;
    }

    public void signUp(String firstName, String lastName) {
        Log.info(repository.getSmsCodeChecked());

        if(isValidFirstLastNames(firstName, lastName)){
            Thread thread = new Thread(() -> {
                view.showLoadingProcess();
                try {
                    repository.signUp(repository.getSmsCodeChecked(), firstName, lastName);
                    repository.signIn(repository.getSmsCodeChecked());
                } catch (IOException e) {
                    e.printStackTrace();
                    view.showError(e.getMessage());
                    view.hideLoadingProcess();
                }
                //view chat if no exceptions
                view.hideLoadingProcess();
                view.goToMainForm();
            });
            thread.start();
        }
    }

    @Override
    public void goBackToPhoneInput() {
        view.goBackToPhoneInput();
    }

    private boolean isValidFirstLastNames(String firstName, String lastName) {
        if (firstName.equals("") || lastName.equals("")) {
            view.showError("Ошибка: заполните Имя и Фамилию");
                return false;
            }
        return true;
    }
}
