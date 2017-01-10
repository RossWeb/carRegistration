mainApp.service('personService', function($http, $q) {
      var person = {
        addressId : "",
        personId : ""
      };

      var persons = {}

      this.getPersons = function(){
        return persons;
      };

      this.setPersons = function(personsList){
        persons = personsList
      };
      this.setAddressId = function(id){
           person.addressId = id;
      };

      this.getAddressId = function() {
         return person.addressId;
      };

      this.setPersonId = function(id){
        person.personId = id;
      };

      this.getPersonId = function(){
        return person.personId;
      };

    this.savePerson = function(dataObj){
        var result = $http.post('person/new', dataObj);
        return deferredResult($q, result);
    };

    this.updatePerson = function(dataObj){
        var result = $http.post('person/edit', dataObj);
        return deferredResult($q, result);
    };

    this.findPersonById = function(dataObj){
        var result = $http.get('person/searchByPesel/' + dataObj);
        return deferredResult($q, result);
    }

    this.removePerson = function(dataObj){
        var result = $http.get('person/delete/'+ dataObj);
        return deferredResult($q, result);
    };

    this.getPersonList =  function(){
        var result = $http.post('person/getAll');
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