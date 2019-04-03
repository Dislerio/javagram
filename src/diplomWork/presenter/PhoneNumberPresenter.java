package diplomWork.presenter;

import diplomWork.Configs;
import diplomWork.model.TLHandler;
import diplomWork.presenter.objects.PhoneFormatError;
import diplomWork.view.forms.PhoneNumber;
import diplomWork.view.forms.VerificationCode;
import diplomWork.view.forms.IView;


public class PhoneNumberPresenter implements IPresenter{        //++
    String phoneNumber;
    PhoneNumber view;
    private static PhoneNumberPresenter instance;

    public static synchronized IPresenter getPresenter(IView iView){
        if(instance == null){
            instance = new PhoneNumberPresenter(iView);
        }
        frame.setContentPane(instance.view.getRootPanel());
        return instance;
    }

    private PhoneNumberPresenter(IView iView){
        this.view = (PhoneNumber) iView;
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
                    VerificationCode.getInstance();
                }
                view.hideLoadingProcess();
            }
        });
        thread.start();
    }
}
