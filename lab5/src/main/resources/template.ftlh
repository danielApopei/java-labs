<!DOCTYPE html>
<html>
<head>
    <title>Files Report</title>
    <link rel="stylesheet" href="./style.css">
    <style>
    body{
        color: white;
        background-color: black;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        padding: 2rem;
    }
    h1 {
        font-size: 3rem;
    }
    li{
        transition: 0.2s all linear;
    }
    li:hover{
        color: #aaa;
        text-decoration: underline;
    }
    </style>
</head>
<body>
    <h1>File Repository</h1>
    <hr>
    <ul>
    <#list files as file>
        <li>${file}</li>
    </#list>
    </ul>
</body>
</html>
