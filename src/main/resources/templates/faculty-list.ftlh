<#import "parts/page.ftlh" as p>

<@p.page "Faculties">

    <table>
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Budget places amount</td>
            <td>Contract Places amount</td>
            <td>Passing grade</td>
            <td>Statement is finalized</td>
        </tr>
        </thead>
        <tbody>
        <#list facultyList as faculty>
            <tr>
                <td>${faculty.id}</td>
                <td>${faculty.name}</td>
                <td>${faculty.budgetPlacesAmount}</td>
                <td>${faculty.contractPlacesAmount}</td>
                <td>${faculty.passingGrade}</td>
                <td>${faculty.statementIsFinalized?string("yes", "no")}</td>
            </tr>
        </#list>
        </tbody>
    </table>

</@p.page>