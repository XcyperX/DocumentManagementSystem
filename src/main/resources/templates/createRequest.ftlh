<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Создать заявку</h5>
                    <div class="card-body">
                        <form id="requestCreate" method="post" class="needs-validation" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="userName">ФИО</label>
                                <input type="text" class="form-control" id="userName" name="userName" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Почта</label>
                                <input type="text" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="form-group">
                                <label for="documentName">Название проекта</label>
                                <input type="text" class="form-control" id="documentName" name="documentName" required>
                            </div>
                            <div class="form-group">
                                <label for="file">Файл</label>
                                <input type="file" class="form-control" id="file" name="file" required>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="requestCreate" type="submit" class="btn btn-success">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        if (window.location.href.includes("error")) {
            alert("Создание заявки не удалось!");
        }
    </script>

</@c.page>