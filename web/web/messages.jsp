<%@ page import="java.util.List" %>
<%@ page import="entity.MessageEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Виктор
  Date: 1/15/14
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        List<MessageEntity> messageEntities = (List<MessageEntity>) request.getAttribute("messages");

    %>
   <table align="center">
       <tr>
           <th>Message Content</th>
           <th>Message Date</th>
       </tr>
       <%
           for(MessageEntity message : messageEntities)
           {
               %>
                <tr>
                    <td align="center">
                        <%=message.getMessageContent()%>
                    </td>
                    <td align="center">
                        <%=message.getMessageDate()%>
                    </td>
                </tr>
                <%
           }
       %>
       <tr>
           <td colspan="2" align="center">
               <a href="index.jsp">Return to main page</a>
           </td>
       </tr>
   </table>
</body>
</html>
