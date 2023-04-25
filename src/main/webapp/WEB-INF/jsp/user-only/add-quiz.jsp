<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add quiz | ${initparam.sitename}</title>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <form action="action" method="POST">
                Question:<textarea  name="question" rows="4" cols="50"></textarea></br>
                A: <input type="radio">test</br>
                B: <input type="radio">test</br>
                C: <input type="radio">test</br>
                D: <input type="radio">test</br>
                <br><input type="submit" value="Save">
            </form>
        </section>
    </body>
</html>
