package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.toIntExact;

public class bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if(message_text.equalsIgnoreCase("/start"))
            {
                System.out.println("start");
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Wellcome to domar bot, please enter your name to continue..");

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
                rowInline.add(new InlineKeyboardButton().setText("Update message text 1 2").setCallbackData("update_msg_text 1 2"));
                rowsInline.add(rowInline);

                List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                rowInline2.add(new InlineKeyboardButton().setText("Update message text 2").setCallbackData("update_msg_text 2"));
                rowsInline.add(rowInline2);

                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("tidak start");
               // System.out.println(update.getCallbackQuery());
                getData(message_text,chat_id);
            }

        }
        else if(update.hasCallbackQuery()) {
            // Set variables
            System.out.println("callback");
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getBotToken() {
        return "1025209902:AAEFZNUKmtp6DfOcoiXJRdOhuN18UpFzNvg";
    }

    public String getBotUsername() {
        return "domar_bot";
    }


    public void getData(final String message_text,final long chat_id) {
      //  System.out.println("masuk1");
        try {
            String url = "http://127.0.0.1:8000/api/";

            Retrofit retrofit;
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);
            Call<List<Customers>> call = service.getData();
            call.enqueue(new Callback<List<Customers>>() {
                public void onResponse(Call<List<Customers>> call, Response<List<Customers>> response) {
                  //  System.out.println("masuk2");
                    List<Customers> newList = response.body();
                    checkNama(newList,message_text,chat_id);
                }
                public void onFailure(Call<List<Customers>> call, Throwable throwable) {
                  //  System.out.println("masuk3");
                    System.out.println(throwable.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void checkNama(List<Customers> customers, String message_text, long chat_id)
    {
        String messageText = "Your name is not found, please enter again..";
        for(int i=0;i<customers.size();i++)
        {
            System.out.println(customers.get(i).getNama());
            if(customers.get(i).getNama().equalsIgnoreCase(message_text))
            {
                messageText = "Your name was found,Thank you..";
            }
        }

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText(messageText);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
