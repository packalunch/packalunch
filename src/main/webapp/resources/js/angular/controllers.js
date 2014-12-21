angular.module('userApp.controllers',[]).controller('UserListController',function($scope,$state,popupService,$window,User){

    $scope.users=User.query();

    $scope.deleteUser=function(user){
        if(popupService.showPopup('Really delete this?')){
            user.$delete(function(){
                //$window.location.href='';
                $state.go('users');
            });
        }

    }

}).controller('AdminListController',function($scope,$state,$stateParams,User){

    $scope.users=User.query();

    $scope.addPayment=function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

}).controller('UserViewController',function($scope,$stateParams,User){

    $scope.user=User.get({id:$stateParams.id});

}).controller('UserCreateController',function($scope,$state,$stateParams,User){

    $scope.user=new User();

    $scope.addUser=function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

}).controller('UserEditController',function($scope,$state,$stateParams,User){

    $scope.updateUser=function(){
        $scope.user.$update(function(){
            $state.go('users');
        });
    };

    $scope.loadUser=function(){
        $scope.user=User.get({id:$stateParams.id});
    };

    $scope.loadUser();
});