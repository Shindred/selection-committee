<#import "parts/page.ftlh" as p>

<@p.page "Registration">


    <form method="post" action="/registration">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group">
            <label for="inputUsername">Username: </label>
                <input type="text" name="username" class="form-control" id="inputUsername" required pattern="[A-z]+">
        </div>
        <div class="form-group">
            <label for="inputFirstName">First name: </label>
                <input type="text" name="firstName" class="form-control" id="inputFirstName" required pattern="[A-z]+">
        </div>
        <div class="form-group">
            <label for="inputLastName">Last name: </label>
                <input type="text" name="lastName" class="form-control" id="inputLastName" required pattern="[A-z]+">
        </div>
        <div class="form-group">
            <label for="inputSecName">Second name: </label>
                <input type="text" name="secondName" class="form-control" id="inputSecName" required pattern="[A-z]+">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password: </label>
                <input type="password" name="password" class="form-control" id="inputPassword" required pattern="[\w\d]+">
        </div>
        <div class="form-group">
            <label for="inputEmail">Email:</label>
                <input type="email" name="email" class="form-control" id="inputEmail">
        </div>
        <div class="form-group">
            <label for="inputCity">City: </label>
                <input type="text" name="city" class="form-control" id="inputCity" required pattern="[A-z]+">
        </div>
        <div class="form-group">
            <label for="inputRegion">Region: </label>
                <input type="text" name="region" class="form-control" id="inputRegion" required pattern="[A-z]+">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</@p.page>