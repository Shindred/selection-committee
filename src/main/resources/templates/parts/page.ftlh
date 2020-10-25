<#macro page name>
    <html lang="en">

    <head>
        <title>${name}</title>
        <link rel="stylesheet" href="/static/w3.css">
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <style>
            body{
                background-image: url("static/images/f859b3178e222f898341c882fb591c7c.png");
                background-repeat: no-repeat;
                background-size: 75%;
                background-position-y: 70px;
                background-position-x: 50%;
            }
        </style>
    </head>
    <body>
    <#nested>
    </body>
    </html>
</#macro>