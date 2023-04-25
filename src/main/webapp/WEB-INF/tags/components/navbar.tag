<%-- 
    Document   : navbar
    Created on : 14-Mar-2023, 10:21:25
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/shortcuts/" prefix="s" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>

<s:CSSReset/>
<s:FontAwesome/>
<s:GeneralCSS filename="navbar"/>

<nav id="nav">
    <header id="nav-header">
        <h1 id="main-page-name">
            <a href="home">${initParam.sitename}</a>
        </h1>
        <ul id="nav-items">
            <li class="nav-item"><a href="home">Home</a></li>
            <li class="nav-item"><a href="home?page=manage-quizzes">Manage Quizzes</a></li>
            <li class="nav-item"><a href="home?page=view-results">View Results</a></li>
        </ul>
    </header>
    <div id="nav-functions">
        <div id="user-functions">
            <c:choose>
                <c:when test="${currentUser == null}">
                    <a href="home?page=login" class="main-button">Login</a>
                </c:when>
                <c:otherwise>
                    <div id="user">
                        <p>Hello ${currentUser.getUsername()}</p>
                        <div class="dropdown">
                            <a href="action?action=logout" class="dropdown-button">Log out</a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>