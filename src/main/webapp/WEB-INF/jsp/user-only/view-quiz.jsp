<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="view-quiz"/>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <h1>${quiz.getTitle()}</h1>
            <p>${quiz.getDescription()}</p>
            <p>Code: [${quiz.getCode()}] - Password: [${quiz.getPassword()}]</p>
            <table>
                <tr>
                    <th>No.</th>
                    <th>Question</th>
                    <th>Answer</th>
                </tr>
                <c:set var="counter" value="1"/>
                <c:forEach var="question" items="${questions}">
                    <c:set var="numberAnswers" value="1"/>
                    <c:forEach var="answer" items="${answers}">
                        <c:if test="${answer.getQuestion_id().equals(question.getId())}">
                            <c:set var="numberAnswers" value="${numberAnswers + 1}"/>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td rowspan="${numberAnswers}">${counter}</td>
                        <td rowspan="${numberAnswers}">${question.getQuestion()}</td>
                    </tr>
                    <c:forEach var="answer" items="${answers}">
                        <c:if test="${answer.getQuestion_id().equals(question.getId())}">
                            <tr>
                                <td style="background-color: ${answer.getIs_correct() ? 'Lime' : 'White'}">${answer.getAnswer()}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:set var="counter" value="${counter + 1}"/>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
