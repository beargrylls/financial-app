<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Black-Scholes European Call Value</title>
    </head>
    <body>
        <form action="ecall" method="POST">    		
    		<table>
			    <tr>
			      <td align="left">Spot price:</td>
			      <td align="left"><input type="text" name="spot" id="spot"/></td>
			    </tr>
			    <tr>
			      <td align="left">Annual risk-free interest rate: </td>
			      <td align="left"><input type="text" name="interest" id="interest"/></td>
			    </tr>
			    <tr>
			      <td align="left">Annual volatility: </td>
			      <td align="left"><input type="text" name="vol" id="vol"/></td>
			    </tr>
			    <tr>
			      <td align="left">Strike: </td>
			      <td align="left"><input type="text" name="strike" id="strike"/> </td>
			    </tr>
			    <tr>
			      <td align="left">Time horizon(years): </td>
			      <td align="left"><input type="text" name="horizon" id="horizon"/> </td>
			    </tr>
			  </table>
    		<input type="submit" value="Compute"/>    		
		</form>

        <p>${call}</p>
        <p style="color:red">${error}</p>
        
        <a href="index.jsp">Back</a>
    </body>
</html>