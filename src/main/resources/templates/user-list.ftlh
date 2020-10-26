<#import "parts/page.ftlh" as p>
<@p.page "User list">
    <#include "parts/navbar.ftlh">
    <table class="table">

        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Username</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Second name</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
            <th scope="col">Region</th>
        </tr>
        </thead>
        <tbody>
        <#list userList as user>
            <tr>
                <th scope="row">${user.id}</th>
                <th scope="row">${user.username}</th>
                <th scope="row">${user.firstName}</th>
                <th scope="row">${user.lastName}</th>
                <th scope="row">${user.secondName}</th>
                <th scope="row">${user.email}</th>
                <th scope="row">${user.city}</th>
                <th scope="row">${user.region}</th>
            </tr>
        </#list>
        </tbody>
    </table>
</@p.page>