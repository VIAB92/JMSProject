import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Виктор on 1/14/14.
 */
public class SendMessage extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try
        {
            Context context = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/tConnectionFactory");
            Queue queue = (Queue) context.lookup("jms/tQueue");
            javax.jms.Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(request.getParameter("message"));
            System.out.println("It come from Servlet: "+message);
            messageProducer.send(message);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sendMesssage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("Servlet send thid message <h2>"+request.getParameter("message")+"</h2> to this Queue: <h2>"+queue.getQueueName()+"</h2>");
            out.println("<a href='index.jsp'>Go to main page</a>'");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        out.close();
    }
}
