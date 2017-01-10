<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div ng-controller = "personController">
        <div class="collapse" id="listPersonExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Lista właścicieli</span></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Numer</th>
                                <th>Pesel</th>
                                <th>Imię</th>
                                <th>Nazwisko</th>
                                <th>Akcja</th>
                            </tr>
                            <tr ng-repeat ="person in persons">
								<td>{{$index + 1}}</td>
								<td>{{person.pesel}}</td>
                                <td>{{person.name}}</td>
                                <td>{{person.surname}}</td>
                                <td>
                                    <button type="button" class="btn btn-success" ng-click="selectPerson(person.pesel)">Wybierz</button>
                                    <button type="button" class="btn btn-danger" ng-click="removePerson(person.pesel)">Usuń</button>
                                </td>
                            </tr>
                         </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="createPersonExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Adres</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="city-addon">Miasto</span>
                            <input id="cityForm" ng-model = "address.city" type="text" class="form-control" placeholder="Miasto" aria-describedby="city-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="street-addon">Ulica</span>
                            <input id="streetForm" ng-model = "address.street" type="text" class="form-control" placeholder="Ulica z numerem" aria-describedby="street-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="postcode-addon">Kod pocztowy</span>
                            <input id="postCodeForm" ng-model = "address.postCode" type="text" class="form-control" placeholder="XX-XXX" aria-describedby="postcode-addon">
                        </div>
                    </div>
                </div>
            </div>
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Właściciel</span></h3>
                            <div class="input-group">
                                <span class="input-group-addon" id="pesel-addon">Pesel</span>
                                <input id="peselForm" ng-model = "owner.pesel" type="text" class="form-control" placeholder="Pesel" aria-describedby="pesel-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="onwerName-addon">Imię</span>
                                <input id="ownerNameForm" ng-model = "owner.name" type="text" class="form-control" placeholder="Imię" aria-describedby="onwerName-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="ownerSurname-addon">Nazwisko</span>
                                <input id="ownerSurnameForm" ng-model = "owner.surname" type="text" class="form-control" placeholder="Nazwisko" aria-describedby="ownerSurname-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="ownerPhoneNumber-addon">Numer telefonu</span>
                                <input id="ownerPhoneNumberForm" ng-model = "owner.phoneNumber" type="text" class="form-control" placeholder="XXXXXXXXX" aria-describedby="ownerPhoneNumber-addon">
                            </div>
                        <button type="button" ng-click="createOwner()" class="btn btn-primary">Utwórz</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="editPersonExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Właściciel</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="city-addon">Miasto</span>
                            <input ng-model = "address.city" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="Miasto" aria-describedby="city-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="street-addon">Ulica</span>
                            <input ng-model = "address.street" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="Ulica z numerem" aria-describedby="street-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="postcode-addon">Kod pocztowy</span>
                            <input ng-model = "address.postCode" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="XX-XXX" aria-describedby="postcode-addon">
                        </div>
                        <div class="input-group">
                                <span class="input-group-addon" id="pesel-addon">Pesel</span>
                                <input ng-model = "owner.pesel" ng-disabled="true" type="text" class="form-control" placeholder="Pesel" aria-describedby="pesel-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="onwerName-addon">Imię</span>
                                <input ng-model = "owner.name" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="Imię" aria-describedby="onwerName-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="ownerSurname-addon">Nazwisko</span>
                                <input ng-model = "owner.surname" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="Nazwisko" aria-describedby="ownerSurname-addon">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" id="ownerPhoneNumber-addon">Numer telefonu</span>
                                <input ng-model = "owner.phoneNumber" ng-disabled="!owner.edit.visible" type="text" class="form-control" placeholder="XXXXXXXXX" aria-describedby="ownerPhoneNumber-addon">
                            </div>
                        <button type="button" ng-hide="owner.edit.visible" ng-click="editOwner()" class="btn btn-primary pull-right">Edytuj</button>
                        <button type="button" ng-show="owner.edit.visible" ng-click="updateOwner()" class="btn btn-primary pull-right">Zapisz</button>
                    </div>
                </div>
            </div>
        </div>
    </div>