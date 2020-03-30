package com.company;

public class UserTelegram {
    String chat_id, name;
    int success;


    public UserTelegram(String chat_id, String name, int success) {
        this.chat_id = chat_id;
        this.name = name;
        this.success = success;
    }

    public String getChat_id() {
        return chat_id;
    }

    public String getName() {
        return name;
    }

    public int getSuccess() {
        return success;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
