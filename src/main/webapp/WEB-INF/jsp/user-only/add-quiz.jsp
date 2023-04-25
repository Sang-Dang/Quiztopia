<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add quiz | ${initparam.sitename}</title>
        <s:CSSReset/>
        <s:jQuery/>
        <style>
            .question-container {
                border: 1px solid black;
                margin: 10px;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <form action="action" method="POST" class="main-form">

                Title<input type="text" name="title"/></br>
                Description<input type="text" name="description"/></br>
                Password<input type="password" name="password" placeholder="Empty for public quiz"/></br>

                <div class="container">
                    <div class="create-question">Create Question</div>
                    <div class="delete-question">Delete Question</div>
                </div>
                <input type='hidden' name='numberOfQuestions' value='0' class='numQuestions'/>
                <input type='hidden' name='action' value='add-quiz'/>
                <br><input type="submit" value="CREATE QUIZ">
            </form>
        </section>
        <script src="resources/js/add-quiz.js"></script>
    </body>
</html>
