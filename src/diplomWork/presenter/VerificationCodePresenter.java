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

public class VerificationCodePresenter implements IPresenter{       //++
    MainFrame frame;
    VerificationCode view;

    public VerificationCodePresenter(IView iView){
        frame = MainFrame.getInstance();
        this.view = (VerificationCode)iView;
        frame.setContentPane(view.getRootPanel());
        sendCode();
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
            ChatForm chat  = ChatForm.getInstance();
            chat.setPresenter(ChatFormPresenter.getPresenter(chat));

        });
        thread.start();

    }

    public void goBackToPhoneInput() {
        PhoneNumber phoneNumber = new PhoneNumber();        //Todo Переделать на синглтон
        phoneNumber.fillPhoneNumberTextField(TLHandler.getInstance().getUserPhone());
        phoneNumber.setPresenter(new PhoneNumberPresenter(phoneNumber));
        TLHandler.getInstance().clearApiBridge();
    }

}
