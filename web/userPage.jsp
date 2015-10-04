<%-- 
    Document   : userPage
    Created on : 04.06.2015, 10:01:02
    Author     : user
--%>

<%@page import="bj.wwHunter.v1.model.Gamers"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="gamer" type="bj.wwHunter.v1.model.Gamers" scope="request"></jsp:useBean>
<jsp:useBean id="penalty" class="bj.wwHunter.v1.model.Penaltys" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <table width="100%"  border="0">
            <tr>
                <td width="420"><form name="form1" method="post" action="StartPage?operation=editGamer">
                        <input type="hidden" name="id" value="${gamer.id}">
                        <table  border="1">
                            <tr>
                                <td width="160"> nick </td>
                                <td width="240"><input type="text" name="nickName" value="${gamer.nickName}"></td>
                            </tr>
                            <tr>
                                <td width="160"> first name </td>
                                <td width="240"><input type="text" name="firstName" value="${gamer.fName}"></td>
                            </tr>
                            <tr>
                                <td width="160"> last name </td>
                                <td width="240"><input type="text" name="lastName" value="${gamer.lName}"></td>
                            </tr>
                            <tr>
                                <td width="160"> sex </td>
                                <td width="240"><select name="sex">
                                        <option${(gamer.sex == "MALE")?" selected":""}>MALE</option>
                                        <option${(gamer.sex == "FEMALE")?" selected":""}>FEMALE</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td width="160"> birthday </td>
                                <td width="240"><input type="text" name="birthday" value="${gamer.birthday}"></td>
                            </tr>
                            <tr>
                                <td width="160"> phone </td>
                                <td width="240"><input type="text" name="phone" value="${gamer.phone}"></td>
                            </tr>
                            <tr>
                                <td width="160"> e-mail </td>
                                <td width="240"><input type="text" name="email" value="${gamer.email}"></td>
                            </tr>
                            <tr>
                                <td width="160"> lifecode </td>
                                <td width="240"><input type="text" name="lifeCode" value="${gamer.lifeCode}"></td>
                            </tr>
                            <tr>
                                <td width="160"> immDateTtime </td>
                                <td width="240"><input type="text" name="stringDateTimeImmunitet" value="${gamer.stringDateTimeImmunitet}"></td>
                            </tr>
                            <tr>
                                <td width="160"> idVictim </td>
                                <td width="240"><input type="text" name="idVictim" value="${gamer.idVictim}"></td>
                            </tr>
                            <tr>
                                <td width="160"> status </td>
                                <td width="240"><select name="status">
                                        <option${(gamer.status == "NOTACTIVE")?" selected":""}>NOTACTIVE</option>
                                        <option${(gamer.status == "ACTIVE")?" selected":""}>ACTIVE</option>
                                        <option${(gamer.status == "DISQUALIFIED")?" selected":""}>DISQUALIFIED</option>
                                        <option${(gamer.status == "DEAD")?" selected":""}>DEAD</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td width="160"> white Zones </td>
                                <td width="240"><textarea name="whiteZones">${gamer.stringWhiteZones}</textarea></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="submit" name="Submit" value="Submit"></td>
                            </tr>
                        </table>
                    </form></td>
                <td><form name="form2" method="post" action="StartPage?operation=editPenalty">
                        <p>active penalty</p>
                        <c:if test="${penalty.id > 0}">
                            <p>penalty: ${penalty}</p>
                            <p>status:
                                <select name="status">
                                    <option${(penalty.status == "LOST")?" selected":""}>LOST</option>
                                    <option${(penalty.status == "ACTIVE")?" selected":""}>ACTIVE</option>
                                    <option${(penalty.status == "EXCELENT")?" selected":""}>EXCELENT</option>
                                </select>
                                <input type="hidden" name="idGamer" value="${gamer.id}">
                                <input type="submit" name="Submit4" value="save">
                            </p>
                        </c:if>
                    </form>
                    <form name="form3" method="post" action="StartPage?operation=addPenalty">
                        <p>penalty</p>
                        <table width="400"  border="1">
                            <tr>
                                <td>date/time</td>
                                <td><input type="text" name="dateTimeMeeting">dd.MM.yyyy HH:mm:ss</td>
                            </tr>
                            <tr>
                                <td>place</td>
                                <td><textarea name="place"></textarea></td>
                            </tr>
                            <tr>
                            <input type="hidden" name="idGamer" value="${gamer.id}">
                            <td colspan="2"><input type="submit" name="Submit5" value="save"></td>
                            </tr>
                        </table>
                    </form>
                    <form name="form4" method="post" action="StartPage?operation=sendAdminMessage">
                        <p>private message</p>
                        <p>text:</p>
                        <p>
                            <textarea name="textMsg"></textarea>
                        </p>
                        <p>
                            <input type="hidden" name="idGamer" value="${gamer.id}">
                            <input type="submit" name="Submit6" value="Submit">
                        </p>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
