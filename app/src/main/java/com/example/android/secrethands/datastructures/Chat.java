package com.example.android.secrethands.datastructures;

/**
 * Created by Aly on 7/29/2019.
 */

public class Chat {
    private String chatId;
    private Message lastMessage;
    private String userId;

    public Chat(String chatId, Message lastMessage, String userId) {
        this.chatId = chatId;
        this.lastMessage = lastMessage;
        this.userId = userId;  //if I am patient so userId is doctor and vice versa
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatId() {
        return chatId;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public String getUserId() {
        return userId;
    }
}
