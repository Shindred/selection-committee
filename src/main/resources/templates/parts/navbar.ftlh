<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Selection Committee</a>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/faculty-list">View faculties</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/user-list">View user list</a>
                </li>
            </#if>
            <#if !notAuthenticated>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log out</a>
                </li>
            </#if>
            <#if notAuthenticated>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Log in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Registration</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text">${name}</div>
    </div>
</nav>