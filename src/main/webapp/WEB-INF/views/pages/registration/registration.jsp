<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div ng-controller = "proofRegistrationController">
         <div class="collapse" id="listRegistrationExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Lista rejestracji</span></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Numer</th>
                                <th>Numer karty pojazdu</th>
                                <th>Numer tablic</th>
                                <th>Data pierwszej rejestracji</th>
                                <th>Właściciel</th>
                                <th>Współwłaściciele</th>
                                <th>Data rejestracji</th>
                                <th>Data ważności rejestracji</th>
                                <th>Samochód</th>
                                <th>Dowód tymczasowy</th>
                                <th>Akcja</th>
                            </tr>
                            <tr ng-repeat ="proofRegistration in proofRegistrations">
                                <td>{{$index + 1}}</td>
                                <td>{{proofRegistration.numberCardVehicle}}</td>
                                <td>{{proofRegistration.plateNumber}}</td>
                                <td>{{proofRegistration.firstRegistrationDate}}</td>
                                <td>{{proofRegistration.mainOwnerPesel}}</td>
                                <td>
                                    <div ng-repeat ="otherOwner in proofRegistration.otherOwnerPesels">
                                        {{otherOwner }}
                                    </div>
                                </td>
                                <td>{{proofRegistration.registrationDate}}</td>
                                <td>{{proofRegistration.registrationValidDate}}</td>
                                <td>{{proofRegistration.carVin}}</td>
                                <td>{{proofRegistration.temporaryProof == true ? 'tak' : 'nie'}}</td>
                                <td>
                                    <button type="button" class="btn btn-success" ng-show="proofRegistration.temporaryProof" ng-click="finalizeProofRegistration(proofRegistration.numberCardVehicle)">Wymień</button>
                                    <button type="button" class="btn btn-danger" ng-click="removeProofRegistration(proofRegistration.numberCardVehicle)">Usuń</button>
                                </td>
                            </tr>
                         </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="createProofRegistrationExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Dowód rejestracyjny</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="isTemporaryProof-addon">Tymczasowy</span>
                            <input type="checkbox" id="isTemporaryProofForm" ng-model = "proofRegistration.isTemporaryProof" disabled="true" class="form-control" aria-describedby="isTemporaryProof-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="numberCardVehicle-addon">Numer karty pojazdu</span>
                            <input id="numberCardVehicleForm" ng-model = "proofRegistration.numberCardVehicle" type="text" disabled="true" class="form-control" placeholder="XXXXXXXXX" aria-describedby="numberCardVehicle-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="plateNumber-addon">Numer tablic</span>
                            <select id="plateNumberForm" ng-model = "proofRegistration.plateNumber" type="text" class="form-control" placeholder="XXXXXXX" aria-describedby="plateNumber-addon">
                                <option ng-repeat="plateNumber in proofRegistration.plateNumbers" value="{{plateNumber}}">{{plateNumber}}</option>
                            </select>
                            <button type="button" ng-click="randomNextPlate()" class="hide btn btn-info pull-right">Losuj</button>
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="firstRegistrationDate-addon">Data pierwszej rejestracji</span>
                            <input id="firstRegistrationDateForm" ng-model = "proofRegistration.firstRegistrationDate" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="firstRegistrationDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="mainOwner-addon">Właściciel</span>
                            <input id="mainOwnerForm" ng-model = "proofRegistration.mainOwnerPesel" disabled="true" type="text" class="form-control" placeholder="MainOwner" aria-describedby="mainOwner-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="otherOwners-addon">Współwłaściciele</span>
                            <input id="otherOwnersForm" ng-model = "proofRegistration.otherOwnerPesels" disabled="true"  type="text" class="form-control" placeholder="OtherOwners" aria-describedby="otherOwners-addon">
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="registrationDate-addon">Data rejestracji</span>
                            <input id="registrationDateForm" ng-model = "proofRegistration.registrationDate" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="registrationDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="car-addon">Samochód</span>
                            <input id="carForm" ng-model = "proofRegistration.carVin" type="text" disabled="true" class="form-control" disabled="true" placeholder="Car" aria-describedby="car-addon">
                        </div>
                        <button type="button" ng-click="createRegistration()" class="btn btn-primary pull-right">Utwórz</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>