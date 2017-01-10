mainApp.service('proofRegistrationService', function($http, $q) {
    var registration = {
        registrationId : '',
    };


    this.getProofRegistrationId = function(){
        return registration.registrationId;
    };

    this.setProofRegistrationId = function(registrationId){
        registration.registrationId = registrationId;
    };

    this.getRegistrationTemplateData = function (dataObj){
        var result = $http.post('registration/getRegistrationTemplateData', dataObj);
        return deferredResult($q, result);
    };

    this.saveRegistration = function(dataObj){
        var result = $http.post('registration/new', dataObj);
        return deferredResult($q, result);
    };

    this.findRegistrationByNumber = function(number){
        var result = $http.post('registration/search/' + number);
        return deferredResult($q, result);
    }

    this.getRegistrationsCount = function(){
        var result = $http.get('registration/getRegistrationsCount');
        return deferredResult($q, result);
    };

    this.deleteRegistration = function(number){
        var result = $http.get('registration/delete/'+ number);
        return deferredResult($q, result);
    };

    this.getRegistrationList =  function(){
        var result = $http.get('registration/getAll');
        return deferredResult($q, result);
    };

});

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