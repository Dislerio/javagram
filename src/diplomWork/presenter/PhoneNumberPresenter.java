package diplomWork.presenter;

public class PhoneNumberPresenter {
    String phoneNumber;
    boolean phoneRegistered;
    boolean phoneAuthenticated;

    boolean checkAuthenticated(){
        return phoneAuthenticated;
    }
    boolean isPhoneRegistered(String phoneNumber){
        return phoneRegistered;
    }
    boolean isPhoneValid(String phoneNumber){
        return phoneNumber.matches("^\\+*[78][9]\\d{9}");
    }
    void showError(){

    }

    void callNextPresenter(){

    }


}
