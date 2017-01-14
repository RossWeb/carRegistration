mainApp.controller('proofRegistrationController', function($scope, $http, proofRegistrationService, carService, insuranceService, personService) {

    $(function () {
        $.datetimepicker.setLocale('pl');
        $('#registrationDateForm').datetimepicker({
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d',
        });
        $('#firstRegistrationDateForm').datetimepicker({
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d',
        });
    });

    var resetForm = function(){
        $scope.proofRegistration.temporaryProof = false;
        $scope.proofRegistration.numberCardVehicle = "";
        $scope.proofRegistration.plateNumber = "init";
        $scope.proofRegistration.firstRegistrationDate = "";
        $scope.proofRegistration.mainOwner = "";
        $scope.proofRegistration.otherOwners = "";
        $scope.proofRegistration.registrationDate = "";

    };

    var getRegistrationList = function(){
        proofRegistrationService.getRegistrationList()
        .then(function (response) {
            $scope.proofRegistrations = response.proofRegistrationList;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var setProofRegistration = function(numberCardVehicle){
        proofRegistrationService.setProofRegistrationId(numberCardVehicle);
    };

    var createProofRegistrationLoadTemplate = function (){
        var registrationTemplateData = {
            pesel : personService.getPersonId()
        };
        proofRegistrationService.getRegistrationTemplateData(registrationTemplateData)
           .then(function (response) {
               $scope.proofRegistration = {};
               $scope.proofRegistration.isTemporaryProof = response.temporary;
               $scope.proofRegistration.numberCardVehicle = response.cardVehicleNumber;
               $scope.proofRegistration.plateNumbers = response.plateNumbers;
               $scope.proofRegistration.mainOwnerPesel = personService.getPersonId();
               $scope.proofRegistration.otherOwnerPesels = insuranceService.getOtherOwnersId();
               $scope.proofRegistration.carVin = carService.getCarId();

           })
           .catch(function (response) {
               alert( "failure message: " + JSON.stringify({data: response}));
           });
    };

    $scope.$on('REGISTRATION_LIST_EVENT', function() {
        getRegistrationList();
    });

    $scope.$on('REGISTRATION_CREATE_EVENT', function() {
        createProofRegistrationLoadTemplate();
    });

    $scope.removeRegistration = function(numberCardVehicle){
       proofRegistrationService.deleteRegistration(numberCardVehicle)
       .then(function (response) {
           getRegistrationList();
       })
       .catch(function (response) {
           alert( "failure message: " + JSON.stringify({data: response}));
       });
    };

    $scope.selectRegistration = function(numberCardVehicle){
        proofRegistrationService.findRegistrationByNumber(numberCardVehicle)
        .then(function (response) {
            setProofRegistration(numberCardVehicle);
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.createRegistration = function(){
        var newRegistrationData = {
            proofRegistrationDto : {
                temporaryProof : $scope.proofRegistration.isTemporaryProof,
                numberCardVehicle : $scope.proofRegistration.numberCardVehicle,
                plateNumber : $scope.proofRegistration.plateNumber,
                firstRegistrationDate : $scope.proofRegistration.firstRegistrationDate,
                mainOwnerPesel : personService.getPersonId(),
                otherOwnerPesels : insuranceService.getOtherOwnersId(),
                registrationDate : $scope.proofRegistration.registrationDate,
                carVin : carService.getCarId()
            }
        };

        proofRegistrationService.saveRegistration(newRegistrationData).
        then(function (response) {
            setProofRegistration(response.proofRegistrationDto.numberCardVehicle);
            getRegistrationList();
            $scope.$emit('REGISTRATION_CREATED_EVENT', '');
            resetForm();
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };
});