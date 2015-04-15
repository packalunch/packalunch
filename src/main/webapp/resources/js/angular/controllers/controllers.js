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



app.controller('UserSuggestController',function($scope,$state,$stateParams,Api) {
    $scope.people =  Api.User.query();

});
