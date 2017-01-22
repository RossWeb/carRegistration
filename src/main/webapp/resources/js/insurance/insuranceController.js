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

    var setInsurance = function(insuranceAgreement){
        insuranceService.setInsuranceId(insuranceAgreement.insuranceNumber);
        insuranceService.addOtherOwnerId(insuranceAgreement.otherOwnerId);
        $scope.$emit('INSURANCE_SET_EVENT', '');
    };

    var fillInsuranceScope = function(insuranceData){
        $scope.insurance = {};
        $scope.insurance.edit = {};
        $scope.insurance.edit.insuranceNumber = insuranceData.insuranceNumber;
        $scope.insurance.edit.purchaseDate = insuranceData.purchaseDate;
        $scope.insurance.edit.insuranceCompanyName = insuranceData.insuranceCompanyName;
        $scope.insurance.edit.owner = insuranceData.buyerPesel;
        $scope.insurance.edit.otherOwners = insuranceData.otherOwnerId;
        $scope.insurance.edit.car = insuranceData.carVin;

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
            if(carService.getCarId() != null){
                setInsurance(response.insuranceAgreementList[0]);
            }
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
            insuranceAgreementDto : {
                insuranceNumber : $scope.insurance.insuranceNumber,
                purchaseDate : $scope.insurance.purchaseDate,
                insuranceCompanyName : $scope.insurance.insuranceCompanyName,
                buyerPesel : personService.getPersonId(),
                otherOwnerId : insuranceService.getOtherOwnersId(),
                carVin : carService.getCarId()
            }
        };

        insuranceService.saveInsurance(newInsuranceData).
        then(function (response) {
            setInsurance(response.insuranceAgreement);
            fillInsuranceScope(response.insuranceAgreement);
            resetForm();
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.updateInsurance = function(){
        var newInsuranceData = {
            insuranceAgreementDto : {
                insuranceNumber : $scope.insurance.edit.insuranceNumber,
                purchaseDate : $scope.insurance.edit.purchaseDate,
                insuranceCompanyName : $scope.insurance.edit.insuranceCompanyName,
                buyerPesel : $scope.insurance.edit.owner,
                otherOwnerId : $scope.insurance.edit.otherOwners,
                carVin : $scope.insurance.edit.car
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