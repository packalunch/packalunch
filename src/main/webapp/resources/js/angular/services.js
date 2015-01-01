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
            User: $resource('currywithari/api/customer/:id', {id:'@id'}, {
                update: { method: 'PUT' }
            }),
            Diner: $resource('currywithari/api/diner/:id', {id: '@id'}),
            Home: $resource('currywithari/api/home/:id', {id: '@id'})
        };
}]).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});