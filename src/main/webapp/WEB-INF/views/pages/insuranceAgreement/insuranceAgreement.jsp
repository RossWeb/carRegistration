<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div ng-controller = "insuranceController">
         <div class="collapse" id="listInsurancesExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Lista ubezpieczeń</span></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Numer</th>
                                <th>Numer ubezpieczenia</th>
                                <th>Data zakupu</th>
                                <th>Nazwa ubezpieczyciela</th>
                                <th>Właściciel</th>
                                <th>Współwłaściciele</th>
                                <th>Akcja</th>
                            </tr>
                            <tr ng-repeat ="insurance in insurances">
                                <td>{{$index + 1}}</td>
                                <td>{{insurance.insuranceNumber}}</td>
                                <td>{{insurance.purchaseDate}}</td>
                                <td>{{insurance.insuranceCompanyName}}</td>
                                <td>{{insurance.buyerPesel}}</td>
                                <td>
                                    <div ng-repeat ="otherOwner in insurance.otherOwnerId">
                                        {{otherOwner }}
                                    </div>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-success" ng-click="selectInsurance(insurance.insuranceNumber)">Wybierz</button>
                                    <button type="button" class="btn btn-danger" ng-click="removeInsurance(insurance.insuranceNumber)">Usuń</button>
                                </td>
                            </tr>
                         </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="createInsuranceExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Ubezpieczenie</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceNumber-addon">Numer ubezpieczenia</span>
                            <input id="insuranceNumberForm" ng-model = "insurance.insuranceNumber" type="text" class="form-control" placeholder="XXXXXXXXX" aria-describedby="insuranceNumber-addon">
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="insurancePurchaseDate-addon">Data zakupu</span>
                            <input id="insurancePurchaseDateForm" ng-model = "insurance.purchaseDate" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="insurancePurchaseDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceCompanyName-addon">Nazwa ubezpieczyciela</span>
                            <input id="insuranceCompanyNameForm" ng-model = "insurance.insuranceCompanyName" type="text" class="form-control" placeholder="CompanyName" aria-describedby="insuranceCompanyName-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceOtherOwner-addon">Współwłaściciele</span>
                            <input ng-model = "insurance.otherOwners" type="text" class="form-control" disabled="true" placeholder="XXXXXXXX" aria-describedby="insuranceOtherOwner-addon">
                            <button type="button" ng-click="addOtherOwner()" class="btn btn-info pull-right">Dodaj</button>
                        </div>
                        <div class="collapse" id="createOtherOwnerExpander" ng-controller = "personController">
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
                                        <button type="button" ng-click="createOtherOwner()" class="btn btn-success">Utwórz właściciela</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="button" ng-click="createInsurance()" class="btn btn-primary pull-right">Utwórz</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="collapse" id="editInsuranceExpander">
            <div class="card card-block">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3><span class="label label-default">Ubezpieczenie</span></h3>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceNumber-addon">Numer ubezpieczenia</span>
                            <input ng-model = "insurance.insuranceNumber" ng-disabled=true type="text" class="form-control" placeholder="XXXXXXXXX" aria-describedby="insuranceNumber-addon">
                        </div>
                        <div class='input-group date'>
                            <span class="input-group-addon" id="insurancePurchaseDate-addon">Data zakupu</span>
                            <input ng-model = "insurance.purchaseDate" ng-disabled="!insurance.edit.visible" type="text" class="form-control" placeholder="XXXX-XX-XX" aria-describedby="insurancePurchaseDate-addon">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceCompanyName-addon">Nazwa ubezpieczyciela</span>
                            <input ng-model = "insurance.insuranceCompanyName" ng-disabled="!insurance.edit.visible" type="text" class="form-control" placeholder="CompanyName" aria-describedby="insuranceCompanyName-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceOwner-addon">Właściciel</span>
                            <input ng-model = "insurance.owner" type="text" ng-disabled=true class="form-control" placeholder="Owner" aria-describedby="insuranceOwner-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceOtherOwner-addon">Współwłaściciele</span>
                            <input type="text" ng-model = "insurance.otherOwners" ng-disabled=true class="form-control" disabled="true" placeholder="XXXXXXXX" aria-describedby="insuranceOtherOwner-addon">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="insuranceCar-addon">Samochód</span>
                            <input ng-model = "insurance.car" type="text" ng-disabled=true class="form-control" placeholder="Car" aria-describedby="insuranceCar-addon">
                        </div>
                        <button type="button" ng-hide="insurance.edit.visible" ng-click="editInsurance()" class="btn btn-primary pull-right">Edytuj</button>
                        <button type="button" ng-show="insurance.edit.visible" ng-click="updateInsurance()" class="btn btn-primary pull-right">Zapisz</button>
                    </div>
                </div>
            </div>
        </div>
    </div>