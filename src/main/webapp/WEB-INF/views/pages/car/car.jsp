<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div ng-controller = "carController">
         <div class="collapse" id="listCarExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Lista samochodów</span></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Numer</th>
                                <th>Vin</th>
                                <th>Nazwa</th>
                                <th>Właściciel</th>
                                <th>Data produkcji</th>
                                <th>Akcja</th>
                            </tr>
                            <tr ng-repeat ="car in cars">
                                <td>{{$index + 1}}</td>
                                <td>{{car.vin}}</td>
                                <td>{{car.name}}</td>
                                <td>{{car.ownerPesel}}</td>
                                <td>{{car.productionDate}}</td>
                                <td>
                                    <button type="button" class="btn btn-success" ng-click="selectCar(car.vin)">Wybierz</button>
                                    <button type="button" class="btn btn-danger" ng-click="removeCar(car.vin)">Usuń</button>
                                </td>
                            </tr>
                         </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="createCarExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Samochód</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="carName-addon">Nazwa</span>
                            <input id="carNameForm" ng-model = "car.name" type="text" class="form-control" placeholder="Nazwa" aria-describedby="carName-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="carVin-addon">Vin</span>
                            <input id="carVinForm" ng-model = "car.vin" type="text" class="form-control" placeholder="XXXXXXXX" aria-describedby="carVin-addon">
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="carProductionDate-addon">Data produkcji</span>
                            <input id="carProductionDateForm" ng-model = "car.productionDate" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="carProductionDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <button type="button" ng-click="createCar()" class="btn btn-primary">Utwórz</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="editCarExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Samochód</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="carName-addon">Nazwa</span>
                            <input ng-model = "car.name" ng-disabled="!car.edit.visible" type="text" class="form-control" placeholder="Nazwa" aria-describedby="carName-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="carVin-addon">Vin</span>
                            <input ng-model = "car.vin" ng-disabled="!car.edit.visible" type="text" class="form-control" placeholder="XXXXXXXX" aria-describedby="carVin-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="carOwnerId-addon">Właściciel</span>
                            <input ng-model = "car.ownerPesel" ng-disabled=true type="text" class="form-control" placeholder="XXXXXXXX" aria-describedby="carOwnerPesel-addon">
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="carProductionDate-addon">Data produkcji</span>
                            <input id="carProductionDateForm" ng-disabled="!car.edit.visible" ng-model = "car.productionDate" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="carProductionDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <button type="button" ng-hide="car.edit.visible" ng-click="editCar()" class="btn btn-primary pull-right">Edytuj</button>
                        <button type="button" ng-show="car.edit.visible" ng-click="updateCar()" class="btn btn-primary pull-right">Zapisz</button>
                    </div>
                </div>
            </div>
        </div>
    </div>