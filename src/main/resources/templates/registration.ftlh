<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-8 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Регистрация</h5>
                    <div class="card-body">
                        <form id="userCreate" class="needs-validation" method="post">
                            <div class="card">
                                <div class="card-header">
                                    Системная информация
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="username">ФИО пользователя</label>
                                        <input type="text" class="form-control" id="username" name="username" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Пароль</label>
                                        <input type="password" class="form-control" id="password" name="password"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Почта</label>
                                        <input type="text" class="form-control" id="email" name="email"
                                                  required>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Роль</label>
                                        <select class="form-control" id="role" name="role">
                                            <option value="MANAGER" selected>Менеджер</option>
                                            <option value="ANALYST">Аналитик</option>
                                            <option value="DIRECTOR">Директор</option>
                                            <option value="ACCOUNTANT">Бухгалтер</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="userCreate" type="submit" class="btn btn-success" >Сохранить</button>
                        <a class="btn btn-danger" href="/users">Отменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        if (window.location.href.includes("error")) {
            alert("Имя пользователя уже существует");
        }
    </script>

</@c.page>