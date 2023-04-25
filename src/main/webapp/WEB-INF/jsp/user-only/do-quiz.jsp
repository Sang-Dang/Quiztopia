<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.fpt.assignment.service.AnswerService" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="do-quiz"/>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <c:set var="counter" value="1"/>
            <form action="action" method="POST">
                <input type="hidden" name="action" value="submit-quiz"/>
                <input type="hidden" name="quizId" value="${quizId}"/>
                <c:forEach var="question" items="${questions}">
                    <div class="card">
                        <p>Question ${counter}</p>
                        <h3>${question.getQuestion()}</h3>
                        <c:set var="answerCounter" value="1"/>
                        <div class="answers">
                            <c:forEach var="answer" items="${AnswerService.getAnswers(question.getId(), answers)}">
                                <div class="answer-item">
                                    <input type="hidden" name="questionId" value="${question.getId()}"/>
                                    <input type="checkbox" name="${question.getId()}" value="${answer.getId()}" id="${answer.getId()}"/>
                                    <label for="${answer.getId()}">${answer.getAnswer()}</label>
                                </div>
                                <c:set var="answerCounter" value="${answerCounter + 1}"/>
                            </c:forEach>
                        </div>
                    </div>
                    <c:set var="counter" value="${counter + 1}"/>
                </c:forEach>
                <input type="submit" value="Submit Quiz"/>
            </form>
        </section>
    </body>
</html>
