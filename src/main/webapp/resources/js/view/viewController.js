mainApp.controller('viewController', function($scope, proofRegistrationService, carService, insuranceService, personService) {

var expanderShow;
var currentStep = 0;


var viewData = [
	person = {
		step : 0,
		nav : false,
		list : {
			selector : $("#listPersonExpander"),
			event : 'PERSON_LIST_EVENT'
		},
		create : {
			selector : $("#createPersonExpander")
		},
		edit : {
			selector : $("#editPersonExpander")
		},
        search : {
            selector : $("#editPersonExpander"),
            event : 'PERSON_SEARCH_EVENT'
        }
	},
	car = {
		step : 1,
		nav : false,
		list : {
			selector : $("#listCarExpander"),
			event : 'CAR_LIST_EVENT'
		},
		create :  {
			selector : $("#createCarExpander")
		},
		edit : {
            selector : $("#editCarExpander")
        },
        search : {
            selector : $("#editCarExpander"),
            event : 'CAR_SEARCH_EVENT'
        }
	},
	insurance = {
		step : 2,
		nav : false,
		list : {
			selector : $("#listInsurancesExpander"),
			event : 'INSURANCE_LIST_EVENT'
		},
		create :  {
			selector : $("#createInsuranceExpander")
		},
        edit : {
             selector : $("#editInsuranceExpander")
        },
        search : {
            selector : $("#editInsuranceExpander"),
            event : 'INSURANCE_SEARCH_EVENT'
        }
	},
	registration = {
		step : 3,
		nav : false,
		list : {
			selector : $("#listRegistrationExpander"),
			event : 'REGISTRATION_LIST_EVENT'
		},
		create :  {
			selector : $("#createProofRegistrationExpander"),
			event : 'REGISTRATION_CREATE_EVENT'
		}
	}

];
var expanderShowBySelector = function(elementNumber, type){
	var expanderShowed = viewData[elementNumber][type].selector;
	expanderShowed.collapse("show");
	if(expanderShow != null){
		expanderShow.collapse("hide");
	}
	expanderShow = expanderShowed;
};

var initView = function(){
	$scope.nav = {};
	$scope.view = {};
	$scope.view.init = {};
	$scope.view.ownerId = 'Brak';
	$scope.view.carId = 'Brak';
	$scope.view.insuranceId = 'Brak';
};

var resetActiveNav = function(){
	for(i = 0; i < viewData.length; i++){
		viewData[i].nav = false;
	}
};

var setActiveNav = function(elementNumber){
	resetActiveNav();
	viewData[elementNumber].nav = true;
	$scope.nav.person = viewData[0].nav;
	$scope.nav.car = viewData[1].nav;
	$scope.nav.insurance = viewData[2].nav;
	$scope.nav.registration	= viewData[3].nav;
};

var mainViewExecute = function(){
	$("#mainExpander").collapse("show");
	expanderShow = $("#mainExpander");
};

$scope.$on('PERSON_SET_EVENT', function() {
    $scope.view.ownerId = personService.getPersonId();
    expanderShowBySelector(0, 'edit');
    currentStep = 1;
});

$scope.$on('CAR_SET_EVENT', function() {
    $scope.view.carId = carService.getCarId();
    expanderShowBySelector(1, 'edit');
    currentStep = 2;
});

$scope.$on('INSURANCE_SET_EVENT', function() {
    $scope.view.insuranceId = insuranceService.getInsuranceId();
    expanderShowBySelector(2, 'edit');
    currentStep = 3;
});

$scope.activeView = function(stepFromView){
	$scope.$broadcast(viewData[stepFromView]['list'].event, '');
	setActiveNav(stepFromView);
	expanderShowBySelector(stepFromView, 'list');
};

$scope.createView = function(elementNumber){
	if(elementNumber <= currentStep){
		expanderShowBySelector(elementNumber, 'create');
		if(currentStep === 3){
			$scope.$broadcast(viewData[3]['create'].event, '');
		}
	}
};

$scope.search = function(data, step){
    $scope.$broadcast(viewData[step]['search'].event, data);
    expanderShowBySelector(step, 'edit');
};

initView();
mainViewExecute();

});