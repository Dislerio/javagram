package diplomWork.presenter;

import diplomWork.view.forms.EditContacts;
import diplomWork.view.forms.MainScreen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditContactPresenter {
    String name;
    String phone;
    String nick;
    MainScreen frame;

    public EditContactPresenter(MainScreen frame){
        this.frame = frame;
    }

    public void runView(String user){
        EditContacts ec = new EditContacts();
        ec.setPresenter(this);
        this.name = user;        //TO DO
        this.phone = "+79042149947";
        ec.setEditUser(name, phone);
        ec.getActionSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goToMainForm();
            }
        });
    }

    void editContact(){

    }
    void showError(){

    }
    boolean validUser(){
        return true;
    }

    void goToMainForm(){
        ChatFormPresenter cp = ChatFormPresenter.getPresenter(frame);
        cp.runView("Что-то там");
    }



}
