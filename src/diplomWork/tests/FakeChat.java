package diplomWork.tests;

public class FakeChat {
    public static String[] getChat() {
        return chat;
    }

    public static ChatLabel[] getChatLabels() {
        return chatLabels;
    }

    static String[] chat = {"Раз", "Два", "Три", "Четыре Пять!", "Вышел заяц погулять", "Вдруг охотник выбегает, прямо в зайчика стреляет!"};
    static ChatLabel[] chatLabels = new ChatLabel[6];
    static {
       for(int i = 0; i < chatLabels.length; i++){
           chatLabels[i] = new ChatLabel(chat[i]);
       }
       chatLabels[0].income = false;
    }
}
