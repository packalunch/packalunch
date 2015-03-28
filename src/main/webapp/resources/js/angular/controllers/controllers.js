app.controller('UserListController',function($scope,$state,popupService,$window,Api){

    $scope.users = Api.User.query();

    $scope.deleteUser = function(user){
        if(popupService.showPopup('Really delete this?')){
            user.$delete(function(){
                $window.location.href='';
//                $state.go('users');
            });
        }

    }

});

app.controller('AdminListController',function($scope,$state,$stateParams,Api){

    $scope.users = Api.Diner.query();

    $scope.addPayment = function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

});


app.controller('UserViewController',function($scope,$stateParams,Api){

    $scope.user = Api.User.get({id:$stateParams.id});

});

//app.controller('LoginController',function($scope,$stateParams,Api) {
//
//    $scope.userAuthenticate = new Api.UserAuthenticate();
//
//    $scope.authenticateUser = function(){
//        console.log($scope.userAuthenticate);
//        $scope.userAuthenticate.$save(function(){
//           // $state.go('users');
//           console.log("HERE--------------");
//        });
//    }
//
//});

app.controller('RegistrationController',function($scope,$stateParams,Api) {

    //todo
    //$scope.userRegister = new Api.UserRegister();
    //
    //$scope.registerUser = function(){
    //    //console.log($scope.userAuthenticate)
    //    //$scope.userAuthenticate.$save(function(){
    //    //   // $state.go('users');
    //    //});
    //}

});

app.controller('UserCreateController',function($scope,$state,$stateParams,Api){

    $scope.user = new Api.User();

    $scope.addUser = function(){
        $scope.user.$save(function(){
            $state.go('users');
        });
    }

});

app.controller('UserEditController',function($scope,$state,$stateParams,Api){

    $scope.updateUser = function(){
        $scope.user.$update(function(){
            $state.go('users');
        });
    };

    $scope.addPayment = function () {
        $scope.userPayment = new Api.DinerPayment();
        $scope.userPayment.id = $scope.user.id;
        $scope.userPayment.accountDto = { payment_amount : $scope.user.accountDto.payment_amount };

        $scope.userPayment.$save(function(){
            $state.go('adminView');
        });

    };

    $scope.loadUser=function(){
        $scope.user = Api.User.get({id:$stateParams.id});
    };

    $scope.loadUser();
});

app.controller('UserSuggestController',function($scope,$state,$stateParams,Api) {
    $scope.people =  Api.User.query();

});

app.controller('UserAddMealController',function($scope,$state,$stateParams,$rootScope,Api,userProperties) {
    console.log ('outside meal ctl user:');

    $scope.addDinerMeal = function () {
        console.log ('add meal ctl user:-----------addDinerMeal');
        $scope.user =  userProperties.getUser();

        //if ($scope.user == null){
        //    //todo: handle user not selected exception.
        //    return false;
        //}

        if ($rootScope.isLoggedIn()){
            console.log("in isLoggedIN");

            $scope.dinerMeal = new Api.DinerMeal();
            $scope.dinerMeal.id = $rootScope.currentUser.id;
            $scope.dinerMeal.dinerSchedule = $scope.weekList;
            console.log ('add meal ctl user:');
            console.log ($scope.dinerMeal);

            $scope.dinerMeal.$save(function(){
                //$state.go('home');
                console.log ('meals saved.');
            });

        } else {
            console.log("in FAILED isLoggedIN");
        }



    };

});
