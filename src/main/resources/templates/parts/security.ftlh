<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.getRoles()?seq_contains("ADMIN")
        notAuthenticated = false
    >
    <#else>
    <#assign
        name = "unknown"
        notAuthenticated = true
        isAdmin = false
        >
</#if>