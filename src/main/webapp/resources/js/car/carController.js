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

    $scope.$on('CAR_LIST_EVENT', function() {
        getCarList();
    });



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
            resetForm();
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };
});