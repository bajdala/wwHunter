<%-- 
    Document   : contacts
    Created on : 04.06.2015, 9:57:26
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Untitled Document</title>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">        
    </head>

    <body>
        <table width="100%" height="100%"  border="0">
            <tr>
                <td rowspan="2">&nbsp;</td>
                <td height="160" colspan="3" align="center" valign="middle"><jsp:include page="_top.jsp"></jsp:include></td>
                    <td rowspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td width="240" align="center" valign="top"><p align="center"><strong><a href="StartPage?operation=index">&#1075;&#1083;&#1072;&#1074;&#1085;&#1072;&#1103;</a></strong></p>
                        <p align="center"><strong><a href="StartPage?operation=rules">&#1086;&#1087;&#1080;&#1089;&#1072;&#1085;&#1080;&#1077; &#1080; &#1087;&#1088;&#1072;&#1074;&#1080;&#1083;&#1072; &#1080;&#1075;&#1088;&#1099;</a></strong></p>
                        <p align="center"><strong><a href="StartPage?operation=regForm">&#1079;&#1072;&#1103;&#1074;&#1082;&#1072; &#1085;&#1072; &#1091;&#1095;&#1072;&#1089;&#1090;&#1080;&#1077;</a></strong></p>
                        <p align="center"><strong>&#1082;&#1086;&#1085;&#1090;&#1072;&#1082;&#1090;&#1085;&#1099;&#1077; &#1076;&#1072;&#1085;&#1085;&#1099;&#1077;</strong></p></td>
                    <td width="544" valign="top">        
                        <p><strong>e-mail:</strong></p>
                        <p><strong>phones:</strong></p>
                        <p><strong>&#1060;&#1048;&#1054;:</strong></p>
                    </td>
                    <td width="240" valign="top">
                    <jsp:include page="_authForm.jsp"></jsp:include>
                </td>
            </tr>
        </table>
    </body>
</html>
