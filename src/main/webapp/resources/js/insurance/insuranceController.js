mainApp.controller('insuranceController', function($scope, $http, insuranceService, carService, personService) {

    $(function () {
        $.datetimepicker.setLocale('pl');
        $('#insurancePurchaseDateForm').datetimepicker({
            timepicker:false,
            format:'Y-m-d',
            formatDate:'Y-m-d',
        });
    });

    var resetForm = function(){
        $scope.insurance.insuranceNumber = "";
        $scope.insurance.purchaseDate = "";
        $scope.insurance.insuranceCompanyName = "";
        $scope.insurance.otherOwners = "";
    };

    var getInsuranceList = function(){
        insuranceService.getInsuranceList()
        .then(function (response) {
            $scope.insurances = response.insuranceAgreementList;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var setInsurance = function(response){
        insuranceService.setInsuranceId(response.insuranceAgreementList[0].insuranceNumber);
        insuranceService.addOtherOwnerId(response.insuranceAgreementList[0].otherOwnerId);
        $scope.$emit('INSURANCE_SET_EVENT', '');
    };

    var fillInsuranceScope = function(insuranceData){
        $scope.insurance = {};
        $scope.insurance.edit = {};
        $scope.insurance.insuranceNumber = insuranceData.insuranceNumber;
        $scope.insurance.purchaseDate = insuranceData.purchaseDate;
        $scope.insurance.insuranceCompanyName = insuranceData.insuranceCompanyName;
        $scope.insurance.owner = insuranceData.buyerPesel;
        $scope.insurance.otherOwner = insuranceData.otherOwnerId;
        $scope.insurance.car = insuranceData.carVin;

    };

    $scope.$on('INSURANCE_LIST_EVENT', function() {
        getInsuranceList();
    });

    $scope.$on('INSURANCE_SEARCH_EVENT', function(nameEvent, insuranceNumber) {
        $scope.selectInsurance(insuranceNumber);
    });

    $scope.addOtherOwner = function(){
        $scope.$on('OTHER_OWNER_CREATED', function(event, data) {
            $("#createOtherOwnerExpander").collapse("hide");
            insuranceService.addOtherOwnerId(data);
            $scope.insurance = {};
            $scope.insurance.otherOwners = insuranceService.getOtherOwnersId();
            console.log('OTHER_OWNER_CREATED fired with data ' + data);
        });
        $("#createOtherOwnerExpander").collapse("show");
    };

    $scope.removeInsurance = function(insuranceNumber){
       insuranceService.deleteInsurance(insuranceNumber)
       .then(function (response) {
           getInsuranceList();
       })
       .catch(function (response) {
           alert( "failure message: " + JSON.stringify({data: response}));
       });
    };

    $scope.selectInsurance = function(insuranceNumber){
        insuranceService.findInsuranceByNumber(insuranceNumber)
        .then(function (response) {
            fillInsuranceScope(response.insuranceAgreementList[0])
            setInsurance(response);
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

     $scope.editInsurance = function(){
        $scope.insurance.edit.visible = true;
    };

    $scope.createInsurance = function(){
        var newInsuranceData = {
            insuranceDto : {
                insuranceNumber : $scope.insurance.insuranceNumber,
                purchaseDate : $scope.insurance.purchaseDate,
                insuranceCompanyName : $scope.insurance.insuranceCompanyName,
                buyer : personService.getPersonId(),
                otherOwner : insuranceService.getOtherOwners(),
                car : carService.getCarId()
            }
        };

        insuranceService.saveInsurance(newInsuranceData).
        then(function (response) {
            setInsurance(response);
            resetForm();
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.updateInsurance = function(){
        var newInsuranceData = {
            insuranceDto : {
                insuranceNumber : $scope.insurance.insuranceNumber,
                purchaseDate : $scope.insurance.purchaseDate,
                insuranceCompanyName : $scope.insurance.insuranceCompanyName,
                buyer : $scope.insurance.owner,
                otherOwner : $scope.insurance.otherOwner,
                car : $scope.insurance.car
            }
        };

        insuranceService.updateInsurance(newInsuranceData).
        then(function (response) {
            $scope.insurance.edit.visible = false;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };
});