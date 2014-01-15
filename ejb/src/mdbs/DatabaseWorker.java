package mdbs;

import entity.MessageEntity;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Виктор on 1/15/14.
 */
public class DatabaseWorker {
    private Connection con;
    public DatabaseWorker()
    {
        java.util.Properties prop = new java.util.Properties();
        prop.put("oracle.jdbc.V8Compatible", "true");
        prop.put("user", "system");
        prop.put("password", "Abacumov3723");

        String url="jdbc:oracle:thin:@localhost:1521:XE";
        String userid="system";
        String userp="Abacumov3723";
        String query = new String("");
        int k=0;
        // загрузка драйвера
        try
        {
            // загрузка Native-драйвера
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("JDBC-Driver is OK!\n");
        }
        catch(java.lang.ClassNotFoundException er)
        {
            System.out.print("JDBC-Driver is wrong!\n");
            System.out.println(er.getMessage());
            er.printStackTrace();
            System.exit(0);
        }
        // установление соединения
        try
        {
            Locale locale = Locale.getDefault();
            Locale.setDefault(Locale.ENGLISH);
            // открытие соединения
            con= DriverManager.getConnection(
                    url, prop);
            System.out.println("Connection is OK!");
            Locale.setDefault(locale);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public String insertMessage(String message, Date data) throws SQLException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sf.format(Calendar.getInstance().getTime());
            Statement stm=con.createStatement();

            String query = "insert into message " +
                    "(content, data)" +
                    " values('"+message+"',to_date('"+date+"','yyyy-mm-dd hh24:mi:ss') )";
            System.out.println(query);
            int k=stm.executeUpdate(query);
            System.out.println("INSERT is OK!");
            stm.close();
        return query;

    }

    public List<MessageEntity> getMessages()
    {
        List<MessageEntity> messages = new ArrayList<MessageEntity>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sf.format(Calendar.getInstance().getTime());
        try
        {
            Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // выполнение запроса
            String query = "select content, data from message where data > to_date('2014-01-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss') AND data < to_date('"+date+"', 'yyyy-mm-dd hh24:mi:ss')";
            System.out.println(query);
            ResultSet rz=stm.executeQuery(query);
            System.out.println("SELECT - OK!");
            System.out.println("Now we'll search results...");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // обработка каждой строки ответа на запрос цикла пока метод next() не вернет false
            while(rz.next())
            {
                System.out.println("Message found!");
                String content = rz.getString(1);
                String dateSel = sdf.format(rz.getTimestamp("data"));
                MessageEntity message = new MessageEntity(content, dateSel);
                messages.add(message);
            }

            rz.close(); // очистка области данных по ответу на запрос
            stm.close(); // очистка данных по запросу

        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return messages;
    }

    public void closeConnection() throws SQLException {
        this.con.close();
    }
}
