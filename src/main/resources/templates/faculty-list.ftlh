<#import "parts/page.ftlh" as p>
<#import "parts/pager.ftlh" as pager>

<@p.page "Faculties">

    <#include "parts/navbar.ftlh">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Budget places amount</th>
            <th scope="col">Contract Places amount</th>
            <th scope="col">Passing grade</th>
            <th scope="col">Statement is finalized</th>
        </tr>
        </thead>
        <tbody>
        <#list facultyList.content as faculty>
            <tr>
                <th scope="row">${faculty.id}</th>
                <th scope="row">${faculty.name}</th>
                <th scope="row">${faculty.budgetPlacesAmount}</th>
                <th scope="row">${faculty.contractPlacesAmount}</th>
                <th scope="row">${faculty.passingGrade}</th>
                <th scope="row">${faculty.statementIsFinalized?string("yes", "no")}</th>
            </tr>
        </#list>
        </tbody>
    </table>
    <@pager.pager url facultyList/>

</@p.page>