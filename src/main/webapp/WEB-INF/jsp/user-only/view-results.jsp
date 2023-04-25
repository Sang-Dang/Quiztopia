<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.fpt.assignment.service.QuizService" %>
<%@page import="com.fpt.assignment.service.ResultService" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your results | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="view-results"/>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <table>
                <tr>
                    <th>Quiz</th>
                    <th>Correct</th>
                    <th>Score</th>
                    <th>Completed</th>
                    <th></th>
                </tr>
                <c:forEach var="result" items="${results}">
                    <tr>
                        <c:set var="quiz" value="${QuizService.getQuizById(result.getQuiz_id().toString())}"/>
                        <td class='tooltip'>
                            ${quiz.getTitle()}
                            <span class='tooltiptext'>${quiz.getCode()}</span>
                        </td>
                        <td>${result.getScore()}</td>
                        <td>${ResultService.calculateScore(result.getQuiz_id(), result.getScore())}%</td>
                        <td>${result.getCompleted_at()}</td>
                        <td><a href="home?page=preview-quiz&id=${quiz.getCode()}"><button>Retry</button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
