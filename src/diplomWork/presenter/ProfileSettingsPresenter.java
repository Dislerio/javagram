package diplomWork.presenter;

import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.PhoneNumber;
import diplomWork.view.forms.ProfileSettings;
import diplomWork.view.forms.IView;

import java.awt.image.BufferedImage;

public class ProfileSettingsPresenter implements IPresenter{
    ProfileSettings view;
    public static ProfileSettingsPresenter presenter;
    private String firstName, lastName;

    public synchronized static ProfileSettingsPresenter getPresenter(IView iView){
        if(presenter == null){
            presenter = new ProfileSettingsPresenter(iView);
        }
        frame.changeOverlayPanel(presenter.view.getRootPanel());
        return presenter;
    }

    private ProfileSettingsPresenter(IView iView){
        this.view = (ProfileSettings) iView;
    }

    public void getUserProfileData() {
        view.fillUserProfileData(repository.getUserFirstName(), repository.getUserLastName(),
                repository.getUserPhone());
        view.fillUserPhoto(repository.getUserPhoto());
    }

    public void setNewUserData(BufferedImage newPhoto, String firstName, String lastName) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        if (isContactFieldsValid()) {
            view.showInfo("Обновление ваших данных на сервере...");
            if (repository.editUserProfile(newPhoto, firstName, lastName)) {
                ChatForm.getInstance().setSelfName(firstName + " " + lastName);
                view.showInfo("Обновление успешно завершено!");
            } else {
                view.showError("При обновлении произошла ошибка!");
            }
        }
    }

    private boolean isContactFieldsValid() {
        //check names
        if (firstName.equals("")) {
            if (lastName.equals("")) {
                view.showError("EmptyFirstLast");
                return false;
            }
            view.showError("EmptyFirstLast");
            return false;
        }
        return true;
    }

    public void logOut() {
        repository.logOut();
        repository.clearApiBridge();
        PhoneNumber.getInstance();
    }

    @Override
    public void goToMainForm() {
        frame.changeOverlayPanel(null);
        frame.showFloatButton();
    }
}
