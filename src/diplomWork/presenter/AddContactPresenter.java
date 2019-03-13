package diplomWork.presenter;

import diplomWork.view.forms.AddContactsForm;
import diplomWork.view.forms.MainScreen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddContactPresenter {
    String name;
    String phone;
    String nick;
    MainScreen frame;

    public AddContactPresenter(MainScreen frame){
        this.frame = frame;
    }

    public void runView(){
        AddContactsForm acf = new AddContactsForm();
        acf.setPresenter(this);
        frame.setContentPane(acf.getRootPanel());
        acf.getAddButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goToMainForm();
            }
        });
    }

    void addContact(){

    }
    void showError(){

    }
    boolean validUser(){
        return true;
    }

    private void goToMainForm(){
        ChatFormPresenter cfp = ChatFormPresenter.getPresenter(frame);
        cfp.runView("Что-то там");
    }





}
