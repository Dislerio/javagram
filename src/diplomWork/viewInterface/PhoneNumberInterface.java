package diplomWork.viewInterface;

public interface PhoneNumberInterface {
    boolean checkAuthenticated();
    boolean isPhoneRegistered(String phoneNumber);
    boolean isPhoneValid(String phoneNumber);
    void showError();

}
