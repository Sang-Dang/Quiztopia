<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Quizzes | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="manage-quiz"/>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <table>
                <tr>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Created at</th>
                    <th>Password</th>
                    <th>Functions</th>
                </tr>
                <c:forEach var="quiz" items="${list}">
                    <tr>
                        <td>${quiz.getCode()}</td>
                        <td>${quiz.getTitle()}</td>
                        <td>${quiz.getDescription()}</td>
                        <td>${quiz.getCreated_at()}</td>
                        <td>${quiz.getPassword()}</td>
                        <td>
                            <a href="home?page=view-quiz&id=${quiz.getCode()}"><button>View Quiz</button></a>
                            <a href="action?action=delete-quiz&id=${quiz.getCode()}"><button>Delete Quiz</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form action="home" method="GET">
                <input type="hidden" name="page" value="add-quiz"/>
                <input type="submit" value="Create quiz"/>
            </form>
        </section>
    </body>
</html>
