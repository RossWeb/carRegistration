mainApp.controller('personController', function($scope, $http, personService, addressService) {



    var resetForm = function(){
        $scope.owner.pesel = "";
        $scope.owner.name = "";
        $scope.owner.surname = "";
        $scope.owner.phoneNumber = "";
        $scope.address.city = "";
        $scope.address.street = "";
        $scope.address.postCode = "";
    };

    var fillPersonScope = function(personData){
        $scope.owner = {};
        $scope.address = {};
        $scope.owner.edit = {};
        $scope.owner.edit.visible = false;
        $scope.owner.pesel = personData.pesel;
        $scope.owner.name = personData.name;
        $scope.owner.surname = personData.surname;
        $scope.owner.phoneNumber = personData.phoneNumber;
        addressService.findAddressById(personData.addressId)
        .then(function (response) {
            $scope.address.city = response.addressDto.city;
            $scope.address.street = response.addressDto.street;
            $scope.address.postCode = response.addressDto.postCode;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var getPersonList = function(){
        personService.getPersonList()
        .then(function (response) {
            $scope.persons = response.personsDto;
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var setOwner = function(response){
        personService.setPersonId(response.personDto.pesel);
        $scope.$emit('PERSON_SET_EVENT', '');
    };

    var updatePerson = function(){
        var newAddressData = {
            addressDto : {
                  city : $scope.address.city,
                  street : $scope.address.street,
                  postCode : $scope.address.postCode
            }
        };

        return addressService.updateAddress(newAddressData)
        .then(function (response) {
            personService.setAddressId(response.addressDto.id);
            var newPersonData = {
                personDto : {
                    pesel : $scope.owner.pesel,
                    name : $scope.owner.name,
                    surname : $scope.owner.surname,
                    phoneNumber : $scope.owner.phoneNumber,
                    addressId : response.addressDto.id
                }
            };

            return personService.updatePerson(newPersonData);
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    var createPerson = function(){
        var newAddressData = {
            addressDto : {
                  city : $scope.address.city,
                  street : $scope.address.street,
                  postCode : $scope.address.postCode
            }
        };

        return addressService.saveAddress(newAddressData)
        .then(function (response) {
            personService.setAddressId(response.addressDto.id);
            var newPersonData = {
                personDto : {
                    pesel : $scope.owner.pesel,
                    name : $scope.owner.name,
                    surname : $scope.owner.surname,
                    phoneNumber : $scope.owner.phoneNumber,
                    addressId : response.addressDto.id
                }
            };

            return personService.savePerson(newPersonData);
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.updateOwner = function(){
        updatePerson();
    };

    $scope.$on('PERSON_LIST_EVENT', function() {
        getPersonList();
    });


    $scope.createOtherOwner = function(){
        var response = createPerson();
        response.then(function (response) {
            resetForm();
            $scope.$emit('OTHER_OWNER_CREATED', response.personDto.pesel);
        });

    };

    $scope.editOwner = function(){
        $scope.owner.edit.visible = true;
    };

    $scope.removePerson = function(pesel){
       personService.removePerson(pesel)
       .then(function (response) {
           getPersonList();
       })
       .catch(function (response) {
           alert( "failure message: " + JSON.stringify({data: response}));
       });
    };

    $scope.selectPerson = function(pesel){
        personService.findPersonById(pesel)
        .then(function (response) {
            fillPersonScope(response.personDto);
            setOwner(response);
        })
        .catch(function (response) {
            alert( "failure message: " + JSON.stringify({data: response}));
        });
    };

    $scope.updateOwner = function(){
        var response = updatePerson();
        response.then(function (response) {
            $scope.owner.edit.visible = false;
        });
    }
	$scope.createOwner = function(){
	    var response = createPerson();
	    response.then(function (response) {
            setOwner(response);
            resetForm();
        });
	};
});