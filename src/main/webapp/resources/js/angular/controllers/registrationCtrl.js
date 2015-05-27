/**
* Created by Sadra on 3/28/15.
*/

app.controller('RegistrationController',function($scope,$location,$stateParams,Api) {

    //$scope.user = Api.SocialAuth.query();
    //console.log($scope.user);

    console.log($scope.userRegister);
    console.log("user registration starting");


    $scope.userRegister = new Api.UserRegister();

    console.log($scope.userRegister);
    console.log("user registration starting");


    $scope.socialRegister = function() {

        console.log($scope.userRegister)
        $scope.userRegister.$save(function(){
           console.log($scope.userRegister);
           console.log("user registration done");
           $location.path('/user');
        });
    }

});