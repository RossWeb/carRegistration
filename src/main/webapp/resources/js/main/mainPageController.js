mainApp.controller('mainPageController', function($scope, proofRegistrationService) {

    var getRegistrationCount = function(){
        proofRegistrationService.getRegistrationsCount()
        .then(function (response) {
            $scope.temporaryProofRegistrationsCount = response.temporaryProofRegistrationsCount;
            $scope.proofRegistrationsCount = response.proofRegistrationsCount;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    getRegistrationCount();
});