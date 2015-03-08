/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

angular.module('userApp.services',[]).factory('Api', ['$resource',
    function($resource) {
        return {
            User: $resource('api/customer/:id', {id:'@id'}, {
                update: { method: 'PUT' },
                query: {method: 'GET', isArray: false }
            }),
            DinerMeal: $resource('api/dinerMeal/:id', {id: '@id'}, {
                query: {method: 'GET', isArray: false }
            }),
            Diner: $resource('api/diner/:id', {id: '@id'}, {
                query: {method: 'GET', isArray: false }
            }),
            DinerPayment: $resource('api/diner/:id/payment', {id: '@id'}, {
                query: {method: 'GET', isArray: false }
            }),
            UserAuthenticate: $resource('login/authenticate/:id', {id: '@id'}, {
                query: {method: 'GET', isArray: false }
            }),
            Home: $resource('api/home/:id', {id: '@id'}, {
                query: {method: 'GET', isArray: false }
            })
        };
}]).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
}).service('userProperties', function () {
    var user;
    return {
        getUser: function () {
            return user;
        },
        setUser: function(value) {
            user = value.originalObject;
        }
    };
});