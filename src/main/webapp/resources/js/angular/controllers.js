angular.module('userApp.controllers',[]).controller('UserListController',function($scope,$state,popupService,$window,Api){

    $scope.users = Api.User.query();

    $scope.deleteUser = function(user){
        if(popupService.showPopup('Really delete this?')){
            user.$delete(function(){
                $window.location.href='';
//                $state.go('users');
            });
        }

    }

}).controller('AdminListController',function($scope,$state,$stateParams,Api){

    $scope.users = Api.Diner.query();

    $scope.addPayment = function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

}).controller('UserViewController',function($scope,$stateParams,Api){

    $scope.user = Api.User.get({id:$stateParams.id});

}).controller('UserCreateController',function($scope,$state,$stateParams,Api){

    $scope.user = new Api.User();

    $scope.addUser = function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

}).controller('UserEditController',function($scope,$state,$stateParams,Api){

    $scope.updateUser = function(){
        $scope.user.$update(function(){
            $state.go('users');
        });
    };

    $scope.loadUser=function(){
        $scope.user = Api.User.get({id:$stateParams.id});
    };

    $scope.loadUser();
});