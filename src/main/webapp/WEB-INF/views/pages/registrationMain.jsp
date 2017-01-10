<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/angular.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="resources/js/mainApp.js"></script>
<script src="resources/js/service/personService.js"></script>
<script src="resources/js/service/addressService.js"></script>
<script src="resources/js/service/carService.js"></script>
<script src="resources/js/service/insuranceService.js"></script>
<script src="resources/js/service/proofRegistrationService.js"></script>
<script src="resources/js/view/viewController.js"></script>
<script src="resources/js/address/addressController.js"></script>
<script src="resources/js/person/personController.js"></script>
<script src="resources/js/car/carController.js"></script>
<script src="resources/js/insurance/insuranceController.js"></script>
<script src="resources/js/proofRegistration/proofRegistrationController.js"></script>
<script src="resources/js/main/mainPageController.js"></script>


</head>
<body ng-app = "mainApp" ng-controller = "viewController">
    <h2>Registration</h2>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    <a class="navbar-brand" href="#">Strona Główna</a>
                </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <div class="row">
	                <ul id="navBar" class="nav navbar-nav">
	                    <li ng-class="{'active': nav.person}" >
	                        <a href="#" ng-click="activeView(0)">Właściciel</a>
	                        <!-- <a href="#" data-toggle="collapse" data-target="#listPersonExpander" aria-expanded="true" aria-controls="listPersonExpander">Właściciel <span class="sr-only">(current)</span></a> -->
	                    </li>
	                    <li ng-class="{'active': nav.car}">
	                    	<a href="#" ng-click="activeView(1)">Samochód</a>
	                        <!-- <a href="#" data-toggle="collapse" data-target="#listCarExpander" aria-expanded="true" aria-controls="listCarExpander">Samochód</a> -->
	                    </li>
	                    <li ng-class="{'active': nav.insurance}">
	                    	<a href="#" ng-click="activeView(2)">Ubezpieczenie</a>
	                        <!-- <a href="#" data-toggle="collapse" data-target="#listInsurancesExpander" aria-expanded="true" aria-controls="listInsurancesExpander">Ubezpieczenie</a> -->
	                    </li>
	                    <li ng-class="{'active': nav.registration}">
	                    	<a href="#" ng-click="activeView(3)">Dowód Rejestracyjny</a>
	                        <!-- <a href="#" data-toggle="collapse" data-target="#listRegistrationExpander" aria-expanded="true" aria-controls="listRegistrationExpander">Dowód Rejestracyjny</a> -->
	                    </li>
	                </ul>
                </div>
                <div class="row">
                	<div class="col-md-5 col-md-offset-1">
		                <form class="navbar-form navbar-left">
		                    <div ng-show="nav.person" id="createPersonTopMenu">
		                        <button id="createUserButton" class="btn btn-default" type="button" ng-click="createView(0)">
		                            Utwórz
		                        </button>
		                        <div class="form-group">
		                            <input type="text" class="form-control" placeholder="Pesel">
		                        </div>
		                        <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#searchUserExpander" aria-expanded="false" aria-controls="searchUserExpander">Szukaj</button>
		                    </div>
		                    <div ng-show="nav.car" id="createCarTopMenu">
		                        <button id="createCarButton" class="btn btn-default" type="button" ng-click="createView(1)">
		                            Utwórz
		                        </button>
		                        <div class="form-group">
		                            <input type="text" class="form-control" placeholder="Numer vin">
		                        </div>
		                        <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#searchCarExpander" aria-expanded="false" aria-controls="searchCarExpander">Szukaj</button>
		                    </div>
		                    <div ng-show="nav.insurance" id="createInsuranceTopMenu">
		                        <button id="createInsuranceButton" class="btn btn-default" type="button" ng-click="createView(2)">
		                            Utwórz
		                        </button>
		                        <div class="form-group">
		                            <input type="text" class="form-control" placeholder="Numer ubezpieczenia">
		                        </div>
		                        <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#searchInsuranceExpander" aria-expanded="false" aria-controls="searchInsuranceExpander">Szukaj</button>
		                    </div>
		                    <div ng-show="nav.registration" id="createRegistrationTopMenu">
		                        <button id="createRegistrationButton" class="btn btn-default" type="button" ng-click="createView(3)">
		                            Utwórz
		                        </button>
		                        <div class="form-group">
		                            <input type="text" class="form-control" placeholder="Numer karty pojazdu">
		                        </div>
		                        <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#searchRegistrationExpander" aria-expanded="false" aria-controls="searchRegistrationExpander">Szukaj</button>
		                    </div>
		                </form>
                	</div>
                	<div class="col-md-5 ">
			            <ul class="nav navbar-nav navbar-right">
			                <div class="panel panel-default">
			  					<div class="panel-body">
			    					<ul>
			    						<li>
			                    			Użytkownik :
			                        		<label id="ownerId">{{view.ownerId}}</label>
			                    		</li>
			                    		<li>
			                    			Samochód :
			                        		<label id="carId">{{view.carId}}</label>
			                    		</li>
					                	<li>
					                    	Ubezpieczenie :
					                        <label id="insuranceId">{{view.insuranceId}}</label>
					                    </li>
								    </ul>
							    </div>
							</div>
			            </ul>
		            </div>
                </div>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
	<div ng-controller = "addressController">
		<div class="collapse" id="searchAddressExpander">
		    <div class="card card-block">
				<h3><span class="label label-default">Adres</span></h3>
				<ul class="list-group">
				    <li id="addressSearchCity" class="list-group-item">
				        <span>Miasto</span>
							<label>{{addressSearched.city}}</label>
                    </li>
				    <li id="addressSearchCity" class="list-group-item">
				        <span>Ulica</span>
						<label>{{addressSearched.street}}</label>
                    </li>
				    <li id="addressSearchCity" class="list-group-item">
				        <span>Kod pocztowy</span>
						<label>{{addressSearched.postCode}}</label>
				    </li>
				</ul>
			</div>
		</div>
    </div>

    <%@ include file="/WEB-INF/views/pages/main.jsp" %>
    <%@ include file="/WEB-INF/views/pages/person/person.jsp" %>
    <%@ include file="/WEB-INF/views/pages/car/car.jsp" %>
    <%@ include file="/WEB-INF/views/pages/insuranceAgreement/insuranceAgreement.jsp" %>
    <%@ include file="/WEB-INF/views/pages/registration/registration.jsp" %>

</body>
</html>