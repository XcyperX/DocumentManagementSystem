<nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light light shadow fixed-top">
    <div class="collapse navbar-collapse" id="navbarNav">
        <#if !(authUser??)>
            <a class="nav-link" href="/requests/create">Создать заявку</a>
            <a class="btn btn-success" href="/login">Войти</a>
        <#else>
            <#if authUser.role == "DIRECTOR">
                <a class="btn btn-primary mr-1" href="/users/registration">Зарегистрировать</a>
                <a class="btn btn-primary mr-1" href="/users">Сотрудники</a>
            </#if>
            <#if (authUser??)>
                <a class="btn btn-info mr-1" href="/requests">Заявки</a>
                <a class="btn btn-success mr-1" href="/requests/approved">Утвержденные заявки</a>
                <a class="btn btn-success mr-1" href="/requests/rejected">Отклоненные заявки</a>
            </#if>
            <a class="btn btn-outline-danger mr-1" href="/logout">Выйти</a>
        </#if>
    </div>
</nav>
<div style='margin-top: 71px'></div>