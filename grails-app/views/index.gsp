<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Link Sharing <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/topic/index">Topic</a></li>
                <li><a href="/subscription/index">Subscription</a></li>
                <li><a href="/resource/index">Resource</a></li>
                <li><a href="/linkresource/index">Link Resource</a></li>
               %{-- <li><a href="/logout">Logout</a></li>--}%
                <li><a href="/login">Login</a>
                </li>
                <li><a href="/user/index">Register</a></li>
            </ul>
        </li>
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>