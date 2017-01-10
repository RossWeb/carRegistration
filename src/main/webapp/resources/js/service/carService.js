mainApp.service('carService', function($http, $q) {
    var car = {
        carId : "",
    };

    this.getCarId = function(){
        return car.carId;
    };

    this.setCarId = function(carId){
        car.carId = carId;
    };


    this.saveCar = function(dataObj){
        var result = $http.post('car/new', dataObj);
        return deferredResult($q, result);
    };

    this.findCarByVin = function(dataObj){
        var result = $http.get('car/searchByVin/' + dataObj);
        return deferredResult($q, result);
    }

    this.removeCar = function(dataObj){
        var result = $http.get('car/delete/'+ dataObj);
        return deferredResult($q, result);
    };

    this.getCarList =  function(){
        var result = $http.get('car/getAll');
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