package com.yellowsoft.newproject;

import java.io.Serializable;

/**
 * Created by mac on 7/16/18.
 */

public class MessageData implements Serializable {

   public String message_date;
    public String message;


    MessageData(String message,String message_date)  {

        this.message = message;
        this.message_date = message_date;


    }


}
