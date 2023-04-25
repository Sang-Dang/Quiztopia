<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.fpt.assignment.service.QuizService" %>
<%@page import="com.fpt.assignment.service.UserService" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="home"/>
        <s:GeneralCSS filename="home1"/>
    </head>
    <body>
        <comp:navbar/>
        <div id="main-container">
            <section id="public-quizzes">
                <table border="1">
                    <tr>
                        <td colspan="100%"><h1>Public Quizzes</h1></td>
                    </tr>
                    <tr>
                        <td colspan="100%">
                            <form action="action" method="GET">
                                <input type="hidden" name="action" value="search-public"/>
                                <input type="text" name="searchterm" placeholder="Enter your searchterm"/>
                                <select name="searchtype">
                                    <option value="search-by-title">Search by Title</option>
                                </select>
                                <input type="submit" value="Search"/>
                            </form>
                        </td>
                    </tr>
                    <tr style="height: 20px;"><td colspan="100%"></td></tr>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th></th>
                    </tr>
                    <c:set var="quizzes" value="${QuizService.getPublicQuizzes()}"/>
                    <c:set var="counter" value="1"/>
                    <c:forEach var="quiz" items="${quizzes}">
                        <tr>
                            <td>${counter}</td>
                            <td>${quiz.getTitle()}</td>
                            <td>${quiz.getDescription()}</td>
                            <td>
                                <form action="home">
                                    <input type="hidden" name="page" value="preview-quiz"/>
                                    <input type="hidden" name="id" value="${quiz.getCode()}"/>
                                    <input type="submit" value="Preview"/>
                                </form>
                            </td>
                        </tr>
                        <c:set value="${counter + 1}" var="counter"/>
                    </c:forEach>
                </table>
            </section>
            <section id="private-quizzes">
                <form action="home" method="get">
                    <input type="hidden" name="page" value="preview-quiz"/>
                    <label for="input-code">Quiz Code:</label>
                    <input type="text" name="id" id="input-code"/>
                    <input type="submit" value="Go"/>
                </form>
            </section>
        </div>
    </body>
</html>
