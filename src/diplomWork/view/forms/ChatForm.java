package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.Log;
import diplomWork.model.objects.Person;
import diplomWork.presenter.ChatFormPresenter;
import diplomWork.presenter.IPresenter;
import diplomWork.tests.*;
import diplomWork.view.components.ChatListRenderer;
import diplomWork.view.components.ChatPanel;
import diplomWork.view.components.ContactListRenderer;
import diplomWork.view.components.ContactPanel;
import diplomWork.viewInterface.IChatForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
import tests.FakeChat;
import tests.FakeContacts;
*/

public class ChatForm implements IChatForm {
    private JPanel contactListPanel;
    private DefaultListModel<ContactPanel> contactListModel;
    private DefaultListModel<Person> clModel;
    private DefaultListModel<ChatPanel> chatListModel;
    private JPanel chatPanel;
    private JPanel rootPanel;
    private JLabel settingsButton;
    private JPanel avatarPanelMini;
    private JPanel chatWithPanel;
    private JPanel chatInfoPanel;
    private JPanel chatAvatarPanel;
    private JPanel chatInputPanel;
    private JPanel sendButtonPanel;
    private JPanel searchButton;
    private JPanel titlePanel;
    private JPanel cList2;
    private JTextArea chatInputField;
    private JTextField searchField;
    private JButton addContactsButton;
    private JLabel iconLabel;
    private JLabel selfNameLabel;
    private JLabel chatWithName;
    private JLabel editContactsButton;
    private JLabel errLabel;
    private JList chatArea;
    private JPanel sPanel;
    private JList contactsList; // контакт лист
    BufferedImage logo, settingsIcon, tavatar, maskBlueMini, maskGray, editButtonIcon, sendButtonIcon, searchButtonIcon, addButtonIcon;
    private ChatFormPresenter presenter;
    private static ChatForm instance;
    private ArrayList<ContactPanel> contactPanels;

    public static ChatForm getInstance(){
        if(instance == null) instance = new ChatForm();
        instance.setPresenter(ChatFormPresenter.getPresenter(instance));
        instance.presenter.getContactList();
        instance.presenter.refreshUserPhotos();
        return instance;
    }

    private ChatForm() {     //отработано
        selfNameLabel.setText("Василий");
        cList2.setLayout(new BoxLayout(cList2, BoxLayout.Y_AXIS));
        JScrollPane contactsScrollPane = new JScrollPane(contactsList);
        cList2.add(contactsScrollPane);
        searchField.setBorder(BorderFactory.createEmptyBorder());
        errLabel.setBackground(Color.BLUE);

        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBorder(BorderFactory.createEmptyBorder());
        chatPanel.add(chatScrollPane);

        addContactsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.callAddPresenter();
            }
        });
        editContactsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.callEditPresenter((Person)contactsList.getSelectedValue());
            }
        });
        MouseAdapter settingsAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                presenter.callSettingsPresenter();
            }
        };
        sPanel.addMouseListener(settingsAdapter);
        avatarPanelMini.addMouseListener(settingsAdapter);
        selfNameLabel.addMouseListener(settingsAdapter);
        settingsButton.addMouseListener(settingsAdapter);
        contactsList.setCellRenderer(new ContactListRenderer());
        contactsList.addListSelectionListener(e -> {
            Person person = (Person) contactsList.getSelectedValue();
            chatWithName.setText(person.getFullName());
            chatAvatarPanel.getGraphics().drawImage(person.getPhotoSmall(),0,0,41,41, null);
            chatAvatarPanel.repaint();
            presenter.getChat(person.getId());
        });
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                showContactListFilter(searchField.getText().trim());
            }
        });
        sendButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Person person = (Person)contactsList.getSelectedValue();
                presenter.sendMessage(chatInputField.getText(), person);
                chatInputField.setText("");
                presenter.getChat(person.getId());
            }
        });
    }

    private void createUIComponents() {   // создание интерфейса
        iconLabel = new JLabel();
        chatArea = new JList();
        contactsList = new JList();
        contactsList.setCellRenderer(new ContactListRenderer());
        contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactsList.setBackground(new Color(230, 230, 230));
        contactsList.setSelectionBackground(Color.white);
        contactsList.setAutoscrolls(false);

        logo = Configs.LOGO_MICRO;
        settingsIcon = Configs.ICON_SETTINGS;
        editButtonIcon = Configs.ICON_EDIT;
        addButtonIcon = Configs.ICON_PLUS;
        tavatar = Configs.tavatar;
        maskBlueMini = Configs.MASK_BLUE_MINI;
        maskGray = Configs.MASK_GRAY;
        sendButtonIcon = Configs.IMG_BUTTON_SEND;
        searchButtonIcon = Configs.IMG_BUTTON_SEARCH;

        iconLabel.setIcon(new ImageIcon(logo));
        settingsButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(settingsIcon, 6, 6, null);
            }
        };
        addContactsButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(addButtonIcon, 0, 0, addContactsButton.getWidth(), addContactsButton.getHeight(), null);
            }
        };
        avatarPanelMini = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tavatar, 0, 0, 30, 30, null);
                g.drawImage(maskBlueMini, 0, 0, 30, 30, null);
            }
        };
        selfNameLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        chatAvatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(tavatar, 0, 0, 41, 41, null);
                g.drawImage(maskGray, 0, 0, null);
            }
        };
        editContactsButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(editButtonIcon, 15, 15, null);
            }
        };
        chatInputField = new JTextArea();
        sendButtonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sendButtonIcon, 0, 0, null);
            }
        };
        searchButton = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(searchButtonIcon, 20, 5, null);
            }
        };


    }

    @Override
    public void setChatList(ArrayList<ChatPanel> panels) {
        //Todo
        Collections.reverse(panels);
        chatArea.setCellRenderer(new ChatListRenderer());       // создание чата
        chatListModel = new DefaultListModel<>();
        chatListModel.ensureCapacity(1);;
        for(ChatPanel panel : panels){
            chatListModel.addElement(panel);
        }
        chatArea.setModel(chatListModel);
        //chatArea.setListData(FakeChat.getChatPanels());
    }

//    public void setContactList(ArrayList<ContactPanel> panels) {
//        //Todo
//        this.contactPanels = panels;
//        contactListModel = new DefaultListModel<>();
//        contactListModel.ensureCapacity(1);;
//        for(ContactPanel panel : panels){
//            contactListModel.addElement(panel);
//        }
//        contactsList.setModel(contactListModel);
//    }

    public void setContactList(DefaultListModel<Person> model) {
        this.clModel = model;
        contactsList.setModel(this.clModel);
        repaintContactList();
    }

    public void repaintContactList(){
        contactsList.repaint();
    }

    public void setSelfUserPhoto(Image photo){
        //Todo
    }
    // стандартные методы

    public void setSelfName(String fullName) {
        selfNameLabel.setText(fullName);
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = (ChatFormPresenter) presenter;
    }

    @Override
    public void showError(String strError) {
        clearError();
        errLabel.setForeground(Color.RED);
        errLabel.setText(strError);
    }

    @Override
    public void showInfo(String strError){
        clearError();
        errLabel.setText(strError);
    }

    @Override
    public void clearError() {
        errLabel.setText("");
        errLabel.setForeground(Color.BLACK);
    }

    @Override
    public void showLoadingProcess() {

    }

    @Override
    public void hideLoadingProcess() {

    }
    private synchronized void showContactListFilter(String searchString) {
        if (searchString.equals("")) {
            contactsList.setModel(clModel);
        } else {
            DefaultListModel<Person> searchModel = new DefaultListModel<>();
            contactsList.setModel(searchModel);
            for (int i = 0; i < clModel.getSize(); i++) {
                if (clModel.get(i).getFullName().toLowerCase().contains(searchString.toLowerCase())) {
                    searchModel.addElement(clModel.get(i));
                }
            }
        }

    }
}
