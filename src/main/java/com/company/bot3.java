package com.company;


import okhttp3.ResponseBody;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sun.rmi.runtime.Log;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bot3 extends TelegramLongPollingBot {

    String rute="";
    String beforeMessage="";
    String beforeMessage2="";


    public String getBotToken() {
        return "1100852729:AAHS6rqi7eiwJ64Desd_MzEwiniHYQNltGE";
    }

    public String getBotUsername() {
        return "notificationTelegram_bot";
    }


    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String name = update.getMessage().getFrom().getFirstName();
            if(message_text.equalsIgnoreCase("/start"))
            {
                saveChatID(chat_id,name);
            }
            else {

            }
        }
        else if(update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            int message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();


            System.out.println(call_data);

        }
    }

    public void saveChatID(final long chat_id, String name){
        try {
            String url = "https://e-learningbot.000webhostapp.com/";

            Retrofit retrofit;
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);
            System.out.println(String.valueOf(chat_id));
            System.out.println(name);
            Call<UserTelegram> call = service.addUser(String.valueOf(chat_id),name);
            call.enqueue(new Callback<UserTelegram>() {
                public void onResponse(Call<UserTelegram> call, Response<UserTelegram> response) {
                    System.out.println(response.body());
                    String message_text ="Chat id has been saved";
                    sendMessage(chat_id,message_text);

                }
                public void onFailure(Call<UserTelegram> call, Throwable throwable) {
                    System.out.println(throwable.getMessage());
                    String message_text ="Error saving chat id, please type /start again";
                    sendMessage(chat_id,message_text);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }





    //fungsi basic send message
    public void sendMessage(long chat_id, String message_text) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText(message_text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
