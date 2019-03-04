package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.view.components.ChatListRenderer;
import diplomWork.view.components.ContactListRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
import tests.FakeChat;
import tests.FakeContacts;
*/

public class MainForm {
    private JPanel titlePanel;
    private JLabel iconLabel;
    private JLabel selfNameLabel;
    private JPanel contactListPanel;
    private JList contactsList;
    private JList list;
    private JPanel chatPanel;
    private JTextArea chatInputField;
    private JList chatArea;
    private JPanel rootPanel;
    private JLabel settingsButton;
    private JPanel avatarPanelMini;
    private JPanel chatWithPanel;
    private JPanel chatInfoPanel;
    private JPanel editContactsButton;
    private JPanel chatAvatarPanel;
    private JLabel chatWithName;
    private JPanel chatInputPanel;
    private JPanel sendButtonPanel;
    private JPanel cList2;
    private JTextField searchField;
    private JPanel searchButton;
    BufferedImage logo, settingsIcon, tavatar, maskBlueMini, maskGray, editButtonIcon, sendButtonIcon, searchButtonIcon;


    public MainForm() {     //отработано
        selfNameLabel.setText("Василий");
        cList2.setLayout(new BoxLayout(cList2, BoxLayout.Y_AXIS));
        searchField.setBorder(BorderFactory.createEmptyBorder());

        //JList list = new JList(FakeContacts.getContactsSample());
        JList list = new JList();
        //list.setListData(FakeContacts.getContactPanels());
        list.setCellRenderer(new ContactListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(new Color(230, 230, 230));
        list.setSelectionBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(list);
        cList2.add(scrollPane);

        chatArea.setCellRenderer(new ChatListRenderer());       // создание чата
        //chatArea.setListData(FakeChat.getChatLabels());

        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBorder(BorderFactory.createEmptyBorder());
        chatPanel.add(chatScrollPane);

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JLabel getSelfNameLabel() {
        return selfNameLabel;
    }

    private void createUIComponents() {   // создание интерфейса
        iconLabel = new JLabel();
        chatArea = new JList();

        logo = Configs.LOGO_MICRO;
        settingsIcon = Configs.ICON_SETTINGS;
        editButtonIcon = Configs.ICON_EDIT;
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
                ;
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
                g.drawImage(tavatar, 0, 0, 41, 41, null);
                g.drawImage(maskGray, 0, 0, null);
            }
        };
        editContactsButton = new JPanel() {
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
}
