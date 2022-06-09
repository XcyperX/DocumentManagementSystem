<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Список новых заявок</h5>
                    <#list documents as document>
                    <div class="card-body">
                        <form id="document${document.id}" class="needs-validation" method="post">
                            <div class="form-group">
                                <label for="userName">ФИО</label>
                                <input type="text" class="form-control" id="userName" name="userName" value="${document.userName}"
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="email">Почта</label>
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${document.email}" required disabled>
                            </div>
                            <div class="form-group mb-0">
                                <a class="btn btn-warning" id="file" href="/download/${document.id}">Скачать проект</a>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="container pl-0">
                            <div class="row">
                                <div class="col-md-auto">
                                    <form action="/requests/${document.id}/accepted" method="post">
                                        <button class="btn btn-success">Принять</button>
                                    </form>
                                </div>
                                <div class="col-md-auto">
                                    <form action="/requests/${document.id}/rejected" method="post">
                                        <button class="btn btn-success">Отклонить</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        if (window.location.href.includes("error")) {
            alert("Ошибка при принятии или отклонении проекта");
        }
    </script>
</@c.page>