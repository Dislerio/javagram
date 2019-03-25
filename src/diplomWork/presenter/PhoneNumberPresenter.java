package diplomWork.presenter;

import diplomWork.Configs;
import diplomWork.model.TLHandler;
import diplomWork.tests.PhoneFormatError;
import diplomWork.view.forms.MainFrame;
import diplomWork.view.forms.PhoneNumber;
import diplomWork.view.forms.VerificationCode;
import diplomWork.viewInterface.IView;


public class PhoneNumberPresenter implements IPresenter{        //++
    String phoneNumber;
    MainFrame frame;
    PhoneNumber view;

    public PhoneNumberPresenter(IView iView){
        frame = MainFrame.getInstance();
        this.view = (PhoneNumber) iView;
        frame.setContentPane(view.getRootPanel());
    }


    public void checkPhone(String phone){
        view.clearError();
        phoneNumber = phone.trim().replaceAll("[^0-9]", "");
        if(phoneNumber.isEmpty()){
            view.showError(Configs.errNoNumber + phone);
            return;
        }
        try{
            if(phoneNumber.length() != Configs.SYS_PHONE_NUMBER_LENGTH){        //999 662 4444
                view.showError(Configs.errPhoneFormat);
                throw new PhoneFormatError(Configs.errPhoneFormat);
            }
        } catch (PhoneFormatError e){
            e.printStackTrace();
            return;
        }

        view.showInfo(Configs.infoConnectingToServer + " " + Configs.TL_SERVER.substring(0, 14));
        view.showLoadingProcess();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //check phone on telegram server
                TLHandler.getInstance().checkPhoneRegistered(phoneNumber);
                if (TLHandler.getInstance().isPhoneRegistered()) {
                    VerificationCode view = new VerificationCode();
                    view.setPresenter(new VerificationCodePresenter(view));
                }
                view.hideLoadingProcess();
            }
        });
        thread.start();
    }

    private boolean isPhoneValid(){
        return phoneNumber.matches("^\\+*[78][9]\\d{9}");
    }

}
