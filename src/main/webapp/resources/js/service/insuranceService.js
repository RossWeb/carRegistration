mainApp.service('insuranceService', function($http, $q) {
    var insurance = {
        insuranceId : '',
        otherOwnersId : []
    };


    this.getInsuranceId = function(){
        return insurance.insuranceId;
    };

    this.setInsuranceId = function(insuranceId){
        insurance.insuranceId = insuranceId;
    };

    this.getOtherOwnersId = function(){
        return insurance.otherOwnersId;
    };

    this.addOtherOwnerId = function(otherOwnerId){
        insurance.otherOwnersId.push(otherOwnerId);
    };

    this.saveInsurance = function(dataObj){
        var result = $http.post('insurance/new', dataObj);
        return deferredResult($q, result);
    };

    this.findInsuranceByNumber = function(number){
        var dataObj = getInsuranceObject(number);
        var result = $http.post('insurance/search' , dataObj);
        return deferredResult($q, result);
    }

    this.deleteInsurance = function(number){
        var result = $http.get('insurance/delete/'+ number);
        return deferredResult($q, result);
    };

    this.getInsuranceList =  function(){
        var result = $http.get('insurance/getAll');
        return deferredResult($q, result);
    };

});

var getInsuranceObject = function(number){
    var response = {
        insuranceAgreementDto : {
            insuranceNumber : number,
            purchaseDate : '',
            insuranceCompanyName : '',
            buyerPesel : '',
            carVin : ''
        }
    }

    return response;
};

var deferredResult = function($q, result){
    var deferred = $q.defer();
    result.success(function(data, status, headers, config) {
        deferred.resolve(data, status, headers, config);
    });
    result.error(function(data, status, headers, config) {
        deferred.reject(result, status, headers, config);
    });
    return deferred.promise;
};