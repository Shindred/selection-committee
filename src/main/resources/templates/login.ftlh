<#import "parts/page.ftlh" as p>

<@p.page "Login">
    <form method="post" action="/login">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group">
            <label for="inputUsername">Username</label>
            <input type="text" class="form-control" name="username" id="inputUsername">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" name="password" id="inputPassword">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <a class="nav-link" href="/registration">Registration</a>
</@p.page>