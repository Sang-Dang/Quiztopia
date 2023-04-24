<%-- 
    Document   : center-float
    Created on : 19-Apr-2023, 20:48:50
    Author     : User
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" body-content="scriptless" %>
<%@attribute name="tagId" required="true"%>
<link rel="stylesheet" href="resources/css/center-float.css"/>

<section class="center-float" id="${tagId}">
    <div class="center-container">
        <div class="close">
            <i class="fa fa-window-close" aria-hidden="true"></i>
        </div>
        <jsp:doBody/>
    </div>
    <script>
        function getTagId() {
            return ${tagId};
        }
    </script>
</section>
<script src="resources/js/center-float.js"></script>
