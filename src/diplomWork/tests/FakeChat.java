package diplomWork.tests;

public class FakeChat {
    public static String[] getChat() {
        return chat;
    }

    static String[] chat = {"Раз", "Два", "Три", "Четыре Пять!", "Вышел заяц погулять и надооооооооооооооооооооооооооолго", "Вдруг охотник выбегает, прямо в зайчика стреляет!"};

    static ChatPanel[] chatPanels = new ChatPanel[chat.length];
    static {
        for(int i = 0; i < chat.length; i++){
            chatPanels[i] = (new ChatPanel(chat[i], (i % 2 == 0 ? true : false)));
        }
    }

    public static ChatPanel[] getChatPanels(){
        return chatPanels;
    }
}
