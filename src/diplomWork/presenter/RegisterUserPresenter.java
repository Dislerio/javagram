package diplomWork.presenter;

import diplomWork.Log;
import diplomWork.model.TLHandler;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.RegForm;
import diplomWork.viewInterface.IView;

import java.io.IOException;

public class RegisterUserPresenter implements IPresenter{
    private MainFrame frame;
    private RegForm view;
    private static RegisterUserPresenter instance;
    private TLHandler repository = TLHandler.getInstance();

    public static RegisterUserPresenter getInstance(IView iView) {
        if(instance ==null){
            instance = new RegisterUserPresenter(iView);
        }
        instance.frame.setContentPane(instance.view.getRootPanel());
        return instance;
    }

    private RegisterUserPresenter(IView iView) {
        frame = MainFrame.getInstance();
        this.view = (RegForm) iView;
    }



    boolean isUserValid(){
        return true;
    }
    boolean UserSignUp(){
        return true;
    }
    void showError(){

    }

    public void signUp(String firstName, String lastName) {
        Log.info(repository.getSmsCodeChecked());

        if(isValidFirstLastNames(firstName, lastName)){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
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
                    view.callViewChat();
                }
            });
            thread.start();
        }
    }

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
