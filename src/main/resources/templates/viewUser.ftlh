<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-8 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Просмотр пользователя</h5>
                    <div class="card-body">
                        <form id="userCreate" class="needs-validation" method="post">
                            <div class="card">
                                <div class="card-header">
                                    Системная информация
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="username">ФИО пользователя</label>
                                        <input type="text" class="form-control" id="username" name="username"
                                               value="${user.username}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Почта</label>
                                        <input type="text" class="form-control" id="email" name="email"
                                                  value="${user.email}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Роль</label>
                                        <select class="form-control" id="role" name="role" disabled>
                                            <#if user.role == "MANAGER">
                                                <option value="MANAGER" selected>Менеджер</option>
                                                <option value="ANALYST">Аналитик</option>
                                                <option value="DIRECTOR">Директор</option>
                                                <option value="ACCOUNTANT">Бухгалтер</option>
                                            </#if>
                                            <#if user.role == "ANALYST">
                                                <option value="MANAGER">Менеджер</option>
                                                <option value="ANALYST" selected>Аналитик</option>
                                                <option value="DIRECTOR">Директор</option>
                                                <option value="ACCOUNTANT">Бухгалтер</option>
                                            </#if>
                                            <#if user.role == "DIRECTOR">
                                                <option value="MANAGER">Менеджер</option>
                                                <option value="ANALYST">Аналитик</option>
                                                <option value="DIRECTOR" selected>Директор</option>
                                                <option value="ACCOUNTANT">Бухгалтер</option>
                                            </#if>
                                            <#if user.role == "ACCOUNTANT">
                                                <option value="MANAGER">Менеджер</option>
                                                <option value="ANALYST">Аналитик</option>
                                                <option value="DIRECTOR">Директор</option>
                                                <option value="ACCOUNTANT" selected>Бухгалтер</option>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <a class="btn btn-warning" href="/users/edit/${user.id}">Изменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>