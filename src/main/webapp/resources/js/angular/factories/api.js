/**
 * Created by sadra on 3/18/15.
 */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */
app.factory('Api', ['$resource',
    function($resource) {
        return {
            User: $resource('api/customer/:id', {id:'@id'}, {
                update: { method: 'PUT' }
            }),
            DinerMeal: $resource('api/dinerMeal/:id', {id: '@id'}),
            Diner: $resource('api/diner/:id', {id: '@id'}),
            DinerPayment: $resource('api/diner/:id/payment', {id: '@id'}),
            UserAuthenticate: $resource('auth/username/:id', {id: '@id'}),
            Home: $resource('home/:id', {id: '@id'})
        };
    }]);