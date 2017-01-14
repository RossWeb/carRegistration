mainApp.controller('carController', function($scope, $http, carService, personService) {

    $(function () {
        $.datetimepicker.setLocale('pl');
        $('#carProductionDateForm').datetimepicker({
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d',
        });
    });

    var resetForm = function(){
        $scope.car.name = "";
        $scope.car.vin = "";
        $scope.car.productionDate = "";
    };

    var getCarList = function(){
        carService.getCarList()
        .then(function (response) {
            $scope.cars = response.carsDto;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var setCar = function(response){
        carService.setCarId(response.carDto.vin);
        $scope.$emit('CAR_SET_EVENT', '');
    };


    var fillCarScope = function(carData){
        $scope.car = {};
        $scope.car.edit = {};
        $scope.car.edit.name = carData.name;
        $scope.car.edit.vin = carData.vin;
        $scope.car.edit.productionDate = carData.productionDate;
        $scope.car.edit.ownerPesel = personService.getPersonId();
    };

    $scope.$on('CAR_LIST_EVENT', function() {
        getCarList();
    });

    $scope.$on('CAR_SEARCH_EVENT', function(nameEvent, vin) {
        $scope.selectCar(vin)
    });

    $scope.editCar = function(){
        $scope.car.edit.visible = true;
    };

    $scope.removeCar = function(vin){
       carService.removeCar(vin)
       .then(function (response) {
           getCarList();
       })
       .catch(function (response) {
           alert( "failure message: " + JSON.stringify({data: response}));
       });
    };

    $scope.selectCar = function(vin){
        carService.findCarByVin(vin)
        .then(function (response) {
            fillCarScope(response.carDto);
            setCar(response);

        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.createCar = function(){
        var newCarData = {
            carDto : {
                name : $scope.car.name,
                vin : $scope.car.vin,
                productionDate : $scope.car.productionDate,
                ownerPesel : personService.getPersonId(),
            }
        };

        carService.saveCar(newCarData).
        then(function (response) {
            setCar(response);
            fillCarScope($scope.car);
            resetForm();
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.updateCar = function(){
        var newCarData = {
            carDto : {
                name : $scope.car.name,
                vin : $scope.car.vin,
                productionDate : $scope.car.productionDate,
                ownerPesel : $scope.car.ownerPesel
            }
        };

        carService.updateCar(newCarData).
        then(function (response) {
            $scope.car.edit.visible = false;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };
});