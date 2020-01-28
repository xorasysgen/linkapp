<div class="nav" role="navigation">
    <ul>
        <sec:ifLoggedIn>
        <li><g:link class="home" controller="main" action="index"><g:message code="default.home.label"/></g:link></li>
        <li><g:link controller="logout" class="list" action="index">Logout</g:link> </li>
        <li><g:link controller="topic" class="list" action="index"><g:message code="default.list.label"  args="['Topic']" /></g:link></li>
        <li><g:link controller="subscription" class="list" action="index"><g:message code="default.list.label" args="['Subscription']" /></g:link></li>
        <li><g:link controller="resource" class="list" action="index"><g:message code="default.list.label" args="['Resource']" /></g:link></li>
         <li><g:link controller="linkResource" class="list" action="index"><g:message code="default.list.label" args="['Linkresource']" /></g:link></li>
            <li><g:link controller="readingItem" class="list" action="index"><g:message code="default.list.label" args="['ReadingItem']" /></g:link></li>

        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li><g:link controller="login" class="list" action="index">Login</g:link> </li>
            <li><g:link controller="user" class="list" action="create">Register</g:link> </li>
        </sec:ifNotLoggedIn>


    </ul>
</div>
%{--

<li><a href="/topic/index">Topic</a></li>
<li><a href="/subscription/index">Subscription</a></li>
<li><a href="/resource/index">Resource</a></li>
<li><a href="/linkresource/index">Link Resource</a></li>
--}%
%{-- <li><a href="/logout">Logout</a></li>--}%%{--

<li><a href="/login">Login</a>
</li>
<li><a href="/user/index">Register</a></li>--}%
