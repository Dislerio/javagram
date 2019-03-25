package diplomWork.presenter;

import diplomWork.Configs;
import diplomWork.model.TLHandler;
import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.PhoneNumber;
import diplomWork.view.forms.VerificationCode;
import diplomWork.viewInterface.IView;
import org.telegram.api.engine.RpcException;

import java.io.IOException;

public class VerificationCodePresenter implements IPresenter{
    MainFrame frame;
    VerificationCode view;
    private static VerificationCodePresenter instance;

    public static VerificationCodePresenter getInstance(IView iView){
        if(instance == null){
            instance = new VerificationCodePresenter(iView);
        }
        instance.frame.setContentPane(instance.view.getRootPanel());
        instance.sendCode();
        return instance;
    }


    private VerificationCodePresenter(IView iView){
        frame = MainFrame.getInstance();
        this.view = (VerificationCode)iView;
    }

    private void sendCode(){
        view.clearError();
        try {
            TLHandler.getInstance().sendCode();
        } catch (IOException e) {
            view.showError(Configs.errSendCode);
            e.printStackTrace();
        }
    }

    public void checkCode(String confirmCode){
        view.showLoadingProcess();
        Thread thread = new Thread(() -> {
            try {
                TLHandler.getInstance().checkCode(confirmCode);
            } catch (RpcException e) {
                if(e.getErrorTag().equals("PHONE_CODE_INVALID")){
                    view.showError(Configs.errPhoneCodeInvalid);
                } else if(e.getErrorTag().equals("PHONE_NUMBER_UNOCCUPIED")){
                    view.showInfo(Configs.errPhoneUnoccupied);
                    view.callViewSignUp();
                } else {
                    view.showError(Configs.errUnknown);
                }
                return;
            } catch (IOException e){
                e.printStackTrace();
                return;
            } finally {
                view.hideLoadingProcess();
            }
            // и если все хорошо - окрываем чат
            ChatForm.getInstance();
        });
        thread.start();

    }

    public void goBackToPhoneInput() {
        PhoneNumber view = PhoneNumber.getInstance();
        view.fillPhoneNumberTextField(TLHandler.getInstance().getUserPhone());
        view.clearError();
        TLHandler.getInstance().clearApiBridge();
    }
}
