<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="error"/>
    </head>
    <body>
        <comp:navbar/>
        <section id="main-container">
            <h1>Error</h1>
            <p>Please try again in a few minutes</p>
        </section>
    </body>
</html>
