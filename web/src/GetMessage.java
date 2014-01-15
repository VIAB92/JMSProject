import entity.MessageEntity;
import mdbs.DatabaseWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виктор on 1/15/14.
 */
public class GetMessage extends HttpServlet {
    private String page = "messages.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseWorker worker = new DatabaseWorker();
        List<MessageEntity> messageEntities = worker.getMessages();
        try {
            worker.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("messages", messageEntities);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        if (requestDispatcher!=null)
        {
            requestDispatcher.forward(request, response);
        }
    }
}
