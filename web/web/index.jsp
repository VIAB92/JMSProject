<%--
  Created by IntelliJ IDEA.
  User: Виктор
  Date: 1/14/14
  Time: 8:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JSP Page</title>
  </head>
  <body>
        <center>
            <form action="sendMessage" method="get">
                <table cellspacing="20">
                    <tbody>
                    <tr>
                        <td>Enter some message: </td>
                        <td><input type="text" name="message" id="message"/></td>
                    </tr>
                    </tbody>
                </table>
                <input type="submit" value="Send message"/>

            </form>
            <a href="getMessage">Get Messages FROM beginning 2014 till today</a>
        </center>
  </body>
</html>
