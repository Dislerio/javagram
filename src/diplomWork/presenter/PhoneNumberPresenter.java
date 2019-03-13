package diplomWork.presenter;

import diplomWork.Loader;
import diplomWork.view.forms.MainForm;
import diplomWork.view.forms.MainScreen;
import diplomWork.view.forms.PhoneNumber;
import diplomWork.view.forms.VerificationCode;

import javax.swing.*;

public class PhoneNumberPresenter {
    String phoneNumber;
    boolean phoneRegistered;
    boolean phoneAuthenticated;
    MainScreen frame;

    public PhoneNumberPresenter(MainScreen frame){
        this.frame = frame;
        runView();
    }

    public void runView(){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPresenter(this);
        frame.setContentPane(phoneNumber.getRootPanel());
        frame.setDefaultCloseOperation(3);
        frame.setSize(900, 630);
        frame.setLocationRelativeTo(null);

        phoneNumber.getContinueButton().addActionListener(e -> {
            //if(presenter.isPhoneRegistered(text.getText())) {
            isPhoneRegistered(phoneNumber.getText());
            callNextPresenter();
            //}
        });
        frame.setVisible(true);
    }

    boolean checkAuthenticated(){
        return phoneAuthenticated;
    }

    public boolean isPhoneRegistered(String phoneNumber){
        this.phoneNumber = phoneNumber;
        if(isPhoneValid()){
            if(checkAuthenticated()){
               phoneRegistered = true;
            }
        } else phoneRegistered = false;
        return phoneRegistered;
    }

    boolean isPhoneValid(){
        return phoneNumber.matches("^\\+*[78][9]\\d{9}");
    }

    public void showError(){

    }

    public void callNextPresenter(){
        VerificationCodePresenter vcp = new VerificationCodePresenter(frame);
        vcp.runView(phoneNumber);

    }


}
