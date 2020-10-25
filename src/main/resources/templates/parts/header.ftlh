<#macro header>
<a class="w3-btn w3-deep-purple w3-left" href="/faculty-list">
    View
</a>
<#--<c:if test="${user == null}">-->
<#--    <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=registration">-->
<#--        <fmt:message key="button.registration"/>-->
<#--    </a>-->
<#--    <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=login">-->
<#--        <fmt:message key="button.sign_in"/>-->
<#--    </a>-->
<#--</c:if>-->
<#--<c:if test="${user != null}">-->
<#--    <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=profile">-->
<#--        <fmt:message key="button.profile"/>-->
<#--    </a>-->
<#--    <c:if test="${user.getRole() == 'ADMIN'}">-->
<#--        <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=userList&page=1">-->
<#--            <fmt:message key="button.view_users"/>-->
<#--        </a>-->
<#--        <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=addFaculty">-->
<#--            <fmt:message key="button.add_faculty"/>-->
<#--        </a>-->
<#--    </c:if>-->
<#--    <a class="w3-btn w3-deep-purple w3-left" href="/controller?command=logout">-->
<#--        <fmt:message key="button.sign_out"/>-->
<#--    </a>-->
<#--</c:if>-->
</#macro>