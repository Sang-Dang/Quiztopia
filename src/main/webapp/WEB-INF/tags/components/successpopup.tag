<%-- 
    Document   : successpopup
    Created on : 15-Mar-2023, 13:14:34
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/shortcuts/" prefix="s" %>
<%@taglib tagdir="/WEB-INF/tags/components/" prefix="comp" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag import="java.net.URLDecoder" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message" required="true"%>

<%-- any content can be specified here e.g.: --%>
<s:jQuery/>
<script src="resources/js/corner-flyout.js"></script>
<c:if test="${message != null && !message.isEmpty()}">
    <comp:cornerflyout color="green" displaymessage="${URLDecoder.decode(message, 'UTF-8')}" flyoutid="success" headmessage="Success" icon="fa fa-check"/>
    <script>
        $(document).ready(function () {
            openFlyout('success');
        });
    </script>
</c:if>