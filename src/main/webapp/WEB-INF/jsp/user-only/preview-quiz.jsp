<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${currentQuiz.getTitle()} | ${initParam.sitename}</title>
        <s:GeneralCSS filename="preview-quiz"/>

    </head>
    <body>
        <comp:navbar/>
        <div id="main-container">
            <h1>${currentQuiz.getTitle()}</h1>
            <p>${currentQuiz.getDescription()}</p>
            <p>Created by ${creator}</p>
            <p>There are ${noquestions} question(s) in this quiz</p>
            <p>Quiz code: ${currentQuiz.getCode()}</p>

            <table>
                <form action="home" method="POST">
                    <input type="hidden" name="page" value="do-quiz"/>
                    <input type="hidden" name="code" value="${currentQuiz.getCode()}"/>
                    <c:if test="${!currentQuiz.getPassword().isEmpty()}">
                        <tr>
                            <td><label for="enter-quiz-password">Enter quiz password:</label></td>
                            <td><input type="password" name="password" placeholder="Enter password" value="" id="enter-quiz-password"/></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2"><input type="submit" value="Start quiz"/></td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>
