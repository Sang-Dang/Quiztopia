<%-- 
    Document   : cornerflyout
    Created on : 15-Mar-2023, 10:18:15
    Author     : User
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/shortcuts/" prefix="s" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="icon" required="true" type="java.lang.String"%>
<%@attribute name="headmessage" required="true" type="java.lang.String" %>
<%@attribute name="displaymessage" required="true" type="java.lang.String" %>
<%@attribute name="color" required="true"  type="java.lang.String" %>
<%@attribute name="flyoutid" required="true" type="java.lang.String" %>

<%-- any content can be specified here e.g.: --%>
<link rel="stylesheet" href="resources/css/corner-flyout.css"/>
<script defer src="resources/js/corner-flyout.js"></script>
<s:CSSReset/>
<s:FontAwesome/>
<s:jQuery/>
<section id="corner-flyouts-container">
    <div class="flyout active" id="${flyoutid}">
        <div class="flyout-icon" style="background-color: ${color};">
            <i class="${icon}"></i>
        </div>
        <div id="text">
            <h3 style="color: ${color}">${headmessage}</h3>
            <p>${displaymessage}</p>
        </div>
        <button type="button" id="close" onclick="closeFlyout('${flyoutid}')"><i class="fa fa-times-circle" aria-hidden="true"></i></button>
    </div>
</section>