<%-- 
    Document   : privatePage
    Created on : 04.06.2015, 10:00:01
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="gamer" type="bj.wwHunter.v1.model.Gamers" scope="request"></jsp:useBean>
<jsp:useBean id="victim" type="bj.wwHunter.v1.model.Gamers" scope="request"></jsp:useBean>
<jsp:useBean id="activity" type="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="penalty" type="bj.wwHunter.v1.model.Penaltys" scope="request"></jsp:useBean>
<jsp:useBean id="penaltyVict" type="bj.wwHunter.v1.model.Penaltys" scope="request"></jsp:useBean>
<jsp:useBean id="immunitet" type="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="victImmunitet" type="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="messages" type="java.util.List" scope="request"></jsp:useBean>
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
                <td height="160" colspan="2" align="center" valign="middle"><jsp:include page="_top.jsp"></jsp:include></td>
                <td rowspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td width="480" align="center" valign="top"><div align="left">
                        <p>${gamer.firstLastNames}</p>
                        <p>последняя игровая активность: ${activity}</p>
                        <p>явка: ${penalty}</p>
                        <p>неуязвимость до: ${immunitet}</p>
                        <form name="form1" method="post" action="StartPage?operation=activateImmunitet">
                            <input type="hidden" name="idGamer" value="${gamer.id}">
                            <input type="submit" name="Submit" value="&#1072;&#1082;&#1090;&#1080;&#1074;&#1080;&#1088;&#1086;&#1074;&#1072;&#1090;&#1100; &#1085;&#1077;&#1091;&#1103;&#1079;&#1074;&#1080;&#1084;&#1086;&#1089;&#1090;&#1100;">
                        </form>
                        <p>Приватные сообщения</p>
                        <form name="form2" method="post" action="">
                            <textarea name="textarea"><c:forEach var="message" items="${messages}">${message}</c:forEach></textarea>
                        </form>
                        <form name="form3" method="post" action="StartPage?operation=sendMessage">
                            <p>сообщение:
                                <input type="hidden" name="idGamer" value="${gamer.id}">
                                <input type="text" name="textMessage">
                            </p>
                            <p>
                                <input type="submit" name="sndVictim" value="&#1086;&#1090;&#1087;&#1088;&#1072;&#1074;&#1080;&#1090;&#1100; &#1078;&#1077;&#1088;&#1090;&#1074;&#1077;">
                                <input type="submit" name="sndHunter" value="&#1086;&#1090;&#1087;&#1088;&#1072;&#1074;&#1080;&#1090;&#1100; &#1086;&#1093;&#1086;&#1090;&#1085;&#1080;&#1082;&#1091;">    
                            </p>
                        </form>
                    </div></td>
                <td width="544" valign="top"><p>Досье на жертву:</p>
                    <p><img src="img/${victim.photo1}" width="400" height="600"><img src="img/${victim.photo2}" width="400" height="600"></p>
                    <p>Ф.И.О.: ${victim.firstLastNames}</p>
                    <p>пол: ${victim.stringSex}</p>
                    <p>год рождения: ${victim.birthday}</p>
                    <p>сухие зоны: ${victim.stringWhiteZones}</p>
                    <p>неуязвимость до: ${victImmunitet}</p>
                    <p>явка: ${penaltyVict}</p>
                    <form name="form4" method="post" action="StartPage?operation=killVictim">
                        код жертвы:
                        <input type="text" name="LC">
                        <input type="hidden" name="idGamer" value="${gamer.id}">
                        <input type="submit" name="Submit4" value="&#1087;&#1086;&#1076;&#1090;&#1074;&#1077;&#1088;&#1076;&#1080;&#1090;&#1100;">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>