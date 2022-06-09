<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">

    <#function locolizedRole user>
        <#if user.role == "MANAGER">
            <#return "Менеджер">
        </#if>
        <#if user.role == "ANALYST">
            <#return "Аналитик">
        </#if>
        <#if user.role == "DIRECTOR">
            <#return "Директор отдела">
        </#if>
            <#if user.role == "ACCOUNTANT">
            <#return "Бухгалтер">
        </#if>
    </#function>

    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th scope="col">ФИО</th>
                    <th scope="col">Почта</th>
                    <th scope="col">Роль</th>
                </tr>
                </thead>
                <#list users as user>
                    <tbody>
                    <tr onclick="openUser(${user.id})">
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${locolizedRole(user)}</td>
                    </tr>
                    </tbody>
                </#list>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        openUser = (id) => {
            window.location.replace('http://localhost:9123/users/' + id);
        }
    </script>

</@c.page>