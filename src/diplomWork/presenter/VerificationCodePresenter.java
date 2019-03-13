package diplomWork.presenter;

import diplomWork.Loader;
import diplomWork.view.forms.MainScreen;
import diplomWork.view.forms.VerificationCode;

public class VerificationCodePresenter {
    String SmsCode;
    String userPhone;
    MainScreen frame;

    public VerificationCodePresenter(MainScreen frame){
        this.frame = frame;
    }

    public void runView(String userPhone){
        this.userPhone = userPhone;
        VerificationCode verificationCode = new VerificationCode(userPhone);
        verificationCode.setPresenter(this);
        frame.setContentPane(verificationCode.getRootPanel());

        verificationCode.getContinueButton().addActionListener(e -> {
            callNextPresenter();
        });

    }



    boolean checkVerificationCode(){
        return true;
    }

    private void callNextPresenter(){
        ChatFormPresenter cfp = ChatFormPresenter.getPresenter(frame);
        cfp.runView(userPhone);
    }

    void showError(){

    }


}
