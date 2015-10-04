<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<table width="100%"  border="0">
  <tr>
    <td width="420"><form name="form1" method="post" action="">
      <table  border="1">
        <tr>
          <td width="160"> nick </td>
          <td width="240"><input type="text" name="textfield"></td>
        </tr>
        <tr>
          <td width="160"> first name </td>
          <td width="240"><input type="text" name="textfield2"></td>
        </tr>
        <tr>
          <td width="160"> last name </td>
          <td width="240"><input type="text" name="textfield3"></td>
        </tr>
        <tr>
          <td width="160"> sex </td>
          <td width="240"><select name="select">
          </select></td>
        </tr>
        <tr>
          <td width="160"> birthday </td>
          <td width="240"><input type="text" name="textfield4"></td>
        </tr>
        <tr>
          <td width="160"> phone </td>
          <td width="240"><input type="text" name="textfield5"></td>
        </tr>
        <tr>
          <td width="160"> e-mail </td>
          <td width="240"><input type="text" name="textfield6"></td>
        </tr>
        <tr>
          <td width="160"> lifecode </td>
          <td width="240"><input type="text" name="textfield7"></td>
        </tr>
        <tr>
          <td width="160"> immDateTtime </td>
          <td width="240"><input type="text" name="textfield8"></td>
        </tr>
        <tr>
          <td width="160"> idVictim </td>
          <td width="240"><input type="text" name="textfield9"></td>
        </tr>
        <tr>
          <td width="160"> status </td>
          <td width="240"><select name="select2">
          </select></td>
        </tr>
        <tr>
          <td width="160"> white Zones </td>
          <td width="240"><textarea name="textarea"></textarea></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" name="Submit" value="Submit"></td>
        </tr>
      </table>
    </form></td>
    <td><form name="form2" method="post" action="">
        <p>active penalty</p>
        <p>date/time: ...</p>
        <p>place: ...</p>
        <p>status:
            <select name="select3">
            </select>
            <input type="submit" name="Submit4" value="save">
        </p>
      </form>
        <form name="form3" method="post" action="">
          <p>penalty</p>
          <table width="400"  border="1">
            <tr>
              <td>date/time</td>
              <td><input type="text" name="textfield11"></td>
            </tr>
            <tr>
              <td>place</td>
              <td><textarea name="textarea2" class="unnamed1"></textarea></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" name="Submit5" value="save"></td>
            </tr>
          </table>
        </form>
        <form name="form4" method="post" action="">
          <p>private message</p>
          <p>text:</p>
          <p>
            <textarea name="textarea3"></textarea>
          </p>
          <p>
            <input type="submit" name="Submit6" value="Submit">
          </p>
        </form>
        <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html>
