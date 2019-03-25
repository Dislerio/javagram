package diplomWork.presenter;

import diplomWork.view.forms.ChatForm;
import diplomWork.view.forms.EditContacts;
import diplomWork.view.forms.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditContactPresenter implements IPresenter{
    String name;
    String phone;
    String nick;
    MainFrame frame;

    public EditContactPresenter(MainFrame frame){
        this.frame = frame;
    }

    public void runView(String user){
        EditContacts ec = new EditContacts();
        ec.setPresenter(this);
        frame.setContentPane(ec.getRootPanel());
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
        frame.setContentPane(ChatForm.getInstance().getRootPanel());
    }



}
