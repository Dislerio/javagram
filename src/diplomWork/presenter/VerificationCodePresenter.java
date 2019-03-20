package diplomWork.presenter;

import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.VerificationCode;

public class VerificationCodePresenter implements IPresenter{
    String SmsCode;
    String userPhone;
    MainFrame frame;

    public VerificationCodePresenter(MainFrame frame){
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
