mainApp.controller('finalizeProofRegistrationModalController', function($scope, close) {

    $scope.dismissModal = function(result) {
        close(result, 500);
    };
    $scope.print = function() {
        $scope.printerStatus = "OK";
    };

});