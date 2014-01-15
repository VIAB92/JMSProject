package mdbs;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Виктор on 1/14/14.
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/tQueue")
}, mappedName="jms/tQueue")
public class TMDB implements javax.jms.MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    public TMDB() {

    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;

        try
        {
            DatabaseWorker worker = new DatabaseWorker();
            if (message instanceof TextMessage)
            {
                msg = (TextMessage) message;
                System.out.println("A message before inserting");
                System.out.println("A message received in TMDB: "+((TextMessage) message).getText());
                Date date = new Date();
                System.out.println(date);
                String query = worker.insertMessage(((TextMessage) message).getText(), new Date());
                System.out.println(query);

            }
            else
            {
                System.out.println("Message of wrong type: "+message.getClass().getName());
                String query = worker.insertMessage("Wrong message type", new Date());
                System.out.println(query);
            }
            worker.closeConnection();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
        catch (SQLException ex)
        {
            System.out.println("SQL Exception: "+ex.toString());
        }
        catch (Throwable te)
        {
            te.printStackTrace();
        }
    }
}
