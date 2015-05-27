/**
 * Created by sadra on 3/28/15.
 */

app.controller('UserViewController',function($scope,$stateParams,Api){


    $scope.user = Api.User.get({id:$stateParams.id});

});

app.controller('UserCtrl',function($scope,$stateParams,Api){
    console.log("after user get00000000000000."  + $stateParams.id);

});



app.controller('UserCreateController',function($scope,$state,$location,$stateParams,Api){

    $scope.user = new Api.User();

    $scope.addUser = function(){
        $scope.user.$save(function(){
            $location.path('/users');
        });
    }

});

app.controller('UserEditController',function($scope,$state,$location,$stateParams,Api){

    $scope.updateUser = function(){
        $scope.user.$update(function(){
            $location.path('/users');
        });
    };

    $scope.addPayment = function () {
        $scope.userPayment = new Api.DinerPayment();
        $scope.userPayment.id = $scope.user.id;
        $scope.userPayment.accountDto = { payment_amount : $scope.user.accountDto.payment_amount };

        $scope.userPayment.$save(function(){
            $location.path('/adminView');
        });

    };

    $scope.loadUser=function(){
        $scope.user = Api.User.get({id:$stateParams.id});
    };

    $scope.loadUser();
});
