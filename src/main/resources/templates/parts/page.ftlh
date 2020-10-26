<#macro page name>
    <html lang="en">

    <head>
        <title>${name}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <meta name="viewport" content="width=device.width, initial-scale=1, shrink-to-fit-no">
    </head>
    <body>
    <div class="container mt-5">
        <#nested>
    </div>
    </body>
    </html>
</#macro>