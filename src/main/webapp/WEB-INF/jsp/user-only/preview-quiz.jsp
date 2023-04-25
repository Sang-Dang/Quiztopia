<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${currentQuiz.getTitle()} | ${initParam.sitename}</title>
    </head>
    <body>
        <comp:navbar/>
        <div id="main-container">
            <h1>${currentQuiz.getTitle()}</h1>
            <p>${currentQuiz.getDescription()}</p>
            <p>Created by ${creator}</p>
            <p>There are ${noquestions} question(s) in this quiz</p>
            <p>Quiz code: ${currentQuiz.getCode()}</p>

            <form action="action" method="POST">
                <input type="hidden" name="action" value="start-quiz"/>
                <input type="hidden" name="id" value="${currentQuiz.getId()}"/>
                <c:if test="${currentQuiz.getPassword() != null}">
                    <label for="enter-quiz-password">Enter quiz password:</label>
                    <input type="password" name="password" placeholder="Enter password" id="enter-quiz-password"/>
                </c:if>
                <input type="submit" value="Start quiz"/>
            </form>
        </div>
    </body>
</html>
