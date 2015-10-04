<%-- 
    Document   : admin
    Created on : 04.06.2015, 10:00:26
    Author     : user
--%>

<%@page import="java.util.Map"%>
<%@page import="bj.wwHunter.v1.model.Gameevents"%>
<%@page import="bj.wwHunter.v1.model.Penaltys"%>
<%@page import="bj.wwHunter.v1.model.News"%>
<%@page import="bj.wwHunter.v1.model.Gamers"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="penaltys" type="java.util.Map<Penaltys, Gamers>" scope="request"></jsp:useBean>
<jsp:useBean id="gameEvents" type="java.util.Map<Gameevents, Gamers[]>" scope="request"></jsp:useBean>
<jsp:useBean id="geStatus" type="java.util.Map<String, String>" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="form1" method="post" action="StartPage?operation=startGame">
            <input type="submit" name="Submit" value="&#1085;&#1072;&#1095;&#1072;&#1090;&#1100; &#1080;&#1075;&#1088;&#1091;">
        </form>

        <table width="100%" height="100%"  border="0">
            <tr>
                <td valign="top">&#1089;&#1087;&#1080;&#1089;&#1086;&#1082; &#1080;&#1075;&#1088;&#1086;&#1082;&#1086;&#1074;
                    <table width="100%"  border="1">
                        <tr>
                            <td><div align="center">&#1060;&#1048;&#1054;</div></td>
                            <td><div align="center">&#1090;&#1077;&#1083;&#1077;&#1092;&#1086;&#1085;</div></td>
                            <td><div align="center">e-mail</div></td>
                            <td><div align="center">&#1089;&#1090;&#1072;&#1090;&#1091;&#1089;</div></td>
                        </tr>
                        <c:forEach items="${gamers}" var="gamer">
                            <tr>
                                <td><a href="StartPage?operation=userPage&id=${gamer.id}">${gamer.firstLastNames}</a></td>
                                <td>${gamer.phone}</td>
                                <td>${gamer.email}</td>
                                <td>${gamer.status}</td>
                            </tr>
                        </c:forEach>                        
                    </table></td>
                <td valign="top">&#1085;&#1086;&#1074;&#1099;&#1081; &#1080;&#1075;&#1088;&#1086;&#1082;
                    <form action="StartPage?operation=addGamer" method="post" enctype="multipart/form-data" name="form2">
                        <table width="100%"  border="0">
                            <tr>
                                <td> nickName: </td>
                                <td><input type="text" name="nickName"></td>
                            </tr>
                            <tr>
                                <td> lastName: </td>
                                <td><input type="text" name="lastName"></td>
                            </tr>
                            <tr>
                                <td> firstName: </td>
                                <td><input type="text" name="firstName"></td>
                            </tr>
                            <tr>
                                <td> sex: </td>
                                <td><select name="sex">
                                        <option>MALE</option>
                                        <option>FEMALE</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td> birthday: </td>
                                <td><input type="text" name="birthday"></td>
                            </tr>
                            <tr>
                                <td> photo1: </td>
                                <td><input type="file" name="photo"></td>
                            </tr>
                            <tr>
                                <td> photo2: </td>
                                <td><input type="file" name="photo2"></td>
                            </tr>
                            <tr>
                                <td> whiteZones: </td>
                                <td><textarea name="whiteZones"></textarea></td>
                            </tr>
                            <tr>
                                <td> phone: </td>
                                <td><input type="text" name="phone"></td>
                            </tr>
                            <tr>
                                <td> email: </td>
                                <td><input type="text" name="email"></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                        </table>
                        <div align="center">
                            <input type="submit" name="Submit2" value="&#1086;&#1090;&#1087;&#1088;&#1072;&#1074;&#1080;&#1090;&#1100; &#1079;&#1072;&#1103;&#1074;&#1082;&#1091;">
                        </div>
                    </form></td>
            </tr>
            <tr>
                <td>&#1086;&#1073;&#1098;&#1103;&#1074;&#1083;&#1077;&#1085;&#1080;&#1103;
                    <table width="100%"  border="1">
                        <tr>
                            <td> <div align="center">дата/время</div></td>
                            <td> <div align="center">текст</div></td>
                            <td> <div align="center">статус</div></td>
                        </tr>
                        <c:forEach items="${news}" var="nw">
                            <tr>
                                <td>${nw.stringDate}</td>
                                <td>${nw.encodedTextNews}</td>
                                <td><form name="form3" method="post" action="StartPage?operation=changeNewsStatus">
                                        <input type="hidden" name="idNews" value="${nw.id}">
                                        <select name="statusNews">
                                            <option${(nw.statusNews == "ACTIVE")?" selected":""}>ACTIVE</option>
                                            <option${(nw.statusNews == "NOTACTIVE")?" selected":""}>NOTACTIVE</option>
                                        </select>
                                        <input type="submit" name="Submit3" value="&#1089;&#1086;&#1093;&#1088;&#1072;&#1085;&#1080;&#1090;&#1100;">
                                    </form></td>
                            </tr>                        
                        </c:forEach>
                    </table></td>
                <td> новое объявление: 
                    <form name="form4" method="post" action="StartPage?operation=addNews">
                        <p>
                            <textarea name="textNews"></textarea>
                        </p>
                        <p>
                            <input type="submit" name="Submit4" value="&#1089;&#1086;&#1093;&#1088;&#1072;&#1085;&#1080;&#1090;&#1100;">
                        </p>
                    </form></td>
            </tr>
            <tr>
                <td colspan="2"> <p>&nbsp;</p>
                    <p>журнал явок: </p>
                    <table width="100%"  border="1">
                        <tr>
                            <td> <div align="center">ФИО игрок >>>> место явки</div></td>
                            <td> <div align="center">дата/время </div></td>
                            <td> <div align="center">статус </div></td>
                        </tr>
                        <c:forEach var="pen" items="${penaltys}">
                            <tr>
                                <td><a href="StartPage?operation=userPage&id=${pen.key.idGamer}">${pen.value.firstLastNames}</a> :>>>> ${pen.key.place}</td>
                                <td>${pen.key.stringDate}</td>
                                <td>${pen.key.status}</td>
                            </tr>
                        </c:forEach>
                    </table></td>
            </tr>
            <tr>
                <td colspan="2"> <p>&nbsp;</p>
                    <p>журнал игровых событий: </p>
                    <table width="100%"  border="1">
                        <tr>
                            <td> дата/время события </td>
                            <td> охотник </td>
                            <td> дичь </td>
                            <td> игровое событие </td>
                        </tr>
                        <c:forEach var="gameEvent" items="${gameEvents}">
                            <tr>
                                <td>${gameEvent.key.stringDate}</td>
                                <td><a href="StartPage?operation=userPage&id=${gameEvent.key.idHunter}">${gameEvent.value[0].firstLastNames}</a></td>
                                <td><a href="StartPage?operation=userPage&id=${gameEvent.key.idVictim}">${gameEvent.value[1].firstLastNames}</a></td>
                                <td>
                                    <c:forEach var="status" items="${geStatus}">
                                        <c:if test="${status.key == gameEvent.key.label}">
                                            ${status.value}
                                        </c:if>
                                    </c:forEach></td>
                            </tr>
                        </c:forEach>
                    </table></td>
            </tr>
        </table>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
    </body>
</html>
