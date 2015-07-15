/**
* Created by Sadra on 3/28/15.
*/

app.controller('RegistrationController',function($scope,$location,$stateParams,Api,authService) {

    //var init = function () {
    //    console.log("controller init");
    //    if (authService.isLoggedIn()){
    //        console.log("user is here");
    //        $location.path('/user');
    //        return;
    //    }
    //};
    //
    //init();

    $scope.registerInternal = function(userRegister) {
        $scope.localUser =  new Api.LocalUser ();
        console.log(userRegister);

        $scope.localUser.firstName = userRegister.firstName;
        $scope.localUser.lastName = userRegister.lastName;
        $scope.localUser.email = userRegister.email;
        $scope.localUser.credentialDto = { password : userRegister.password };

        console.log( $scope.localUser);

        $scope.localUser.$save(function(){

            console.log($scope.localUser);

            if ($scope.localUser.status == 'success') {
                console.log("user save successfully: " + $scope.localUser.message);
                $location.path('/user');
            } else if ($scope.localUser.status == 'failed') {
                console.log("user register failed: " + $scope.localUser.message);
                $scope.message = $scope.localUser.message;
            }

        });

    };

    //
    //$scope.addPayment = function () {
    //    $scope.userPayment = new Api.DinerPayment();
    //    $scope.userPayment.id = $scope.user.id;
    //    $scope.userPayment.accountDto = { payment_amount : $scope.user.accountDto.payment_amount };
    //
    //
    //
    //};


    $scope.socialRegister = function() {
        $scope.userRegister = new Api.UserRegister();
        console.log($scope.userRegister);
        $scope.userRegister.$save(function(){
           console.log($scope.userRegister);
           console.log("user registration done");
           $location.path('/user');
        });
    }

});