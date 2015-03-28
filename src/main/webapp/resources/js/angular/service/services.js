

//angular.module('userApp.services',[])
//    .factory('Api', ['$resource',
//    function($resource) {
//        return {
//            User: $resource('api/customer/:id', {id:'@id'}, {
//                update: { method: 'PUT' }
//            }),
//            DinerMeal: $resource('api/dinerMeal/:id', {id: '@id'}),
//            Diner: $resource('api/diner/:id', {id: '@id'}),
//            DinerPayment: $resource('api/diner/:id/payment', {id: '@id'}),
//            UserAuthenticate: $resource('login/authenticate/:id', {id: '@id'}),
//            Home: $resource('api/home/:id', {id: '@id'})
//        };
//}])

app.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});

app.service('userProperties', function () {
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