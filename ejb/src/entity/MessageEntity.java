package entity;

import java.util.Date;

/**
 * Created by Виктор on 1/15/14.
 */
public class MessageEntity {
    private String messageContent;
    private String messageDate;

    public MessageEntity(String messageContent, String messageDate)
    {
        this.messageContent=messageContent;
        this.messageDate=messageDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getMessageDate() {
        return messageDate;
    }

}
