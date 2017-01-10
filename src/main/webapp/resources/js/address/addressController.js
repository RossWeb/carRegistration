mainApp.controller('addressController', function($scope, $http, addressService) {

    var resetForm = function(){
        $scope.address.city = "";
        $scope.address.street = "";
        $scope.address.postCode = "";
    };
//    var newAddressData = {
//        addressDto : {
//              city : $scope.address.city,
//              street : $scope.address.street,
//              postCode : $scope.address.postCode
//        }
//    };
//    addressService.saveData(newAddressData);

	var setAddressById = function(id){
	    var addressSearch;
        var res = $http.post('person/address/searchById?id=' + id);
        res.success(function(data, status, headers, config) {
            $scope.addressSearched = {};
            $scope.addressSearched.city = data.addressDto.city;
            $scope.addressSearched.street = data.addressDto.street;
            $scope.addressSearched.postCode = data.addressDto.postCode;
        });
        res.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });
	};
});