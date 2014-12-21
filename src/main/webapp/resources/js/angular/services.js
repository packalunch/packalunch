/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

angular.module('userApp.services',[]).factory('User',function($resource){
    return $resource('currywithari/api/customer/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });

}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});