<%@taglib prefix="s" tagdir="/WEB-INF/tags/shortcuts/" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | ${initParam.sitename}</title>
        <s:CSSReset/>
        <s:GeneralCSS filename="login"/>
    </head>
    <body>
        <comp:navbar></comp:navbar>
        <div id="main-container">
            <section id="login-section">
                <h2>Login</h2>
                <form action="action" method="POST">
                    <input type="hidden" name="action" value="login"/>
                    <label for="input-login-username">Username:</label>
                    <input type="text" name="username" class="inputfield" id="input-login-username" />
                    <label for="input-login-password">Password:</label>
                    <input type="password" name="password" class="inputfield" id="input-login-password" />
                    <input type="submit" value="Log in"/>
                </form>
            </section>
            <section id="register-section">
                <h2>Register</h2>
                <form action="action" method="POST">
                    <input type="hidden" name="action" value="register"/>
                    <label for="input-register-email">Email:</label>
                    <input type="text" name="email" class="inputfield" id="input-register-email"/>
                    <label for="input-register-username">Username:</label>
                    <input type="text" name="username" class="inputfield" id="input-register-username"/>
                    <label for="input-register-password">Password</label>
                    <input type="password" name="password" class="inputfield" id="input-register-password"/>
                    <input type="submit" value="Register account"/>
                </form>
            </section>
        </div>
    </body>
</html>
