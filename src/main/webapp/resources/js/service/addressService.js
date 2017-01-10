mainApp.service('addressService', function($http, $q) {

  this.saveAddress = function(dataObj){
		var result = $http.post('person/address/new', dataObj);
    return deferredResult($q, result);

  };

  this.updateAddress = function(dataObj){
    var result = $http.post('person/address/edit', dataObj);
    return deferredResult($q, result);

  };

  this.findAddressById = function(id){
      var result = $http.post('person/address/searchById?id=' + id);
      return deferredResult($q, result);
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
});