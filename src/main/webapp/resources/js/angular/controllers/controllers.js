app.controller('UserListController',function($scope,$state,$location,popupService,$window,Api){

    $scope.users = Api.User.query();

    $scope.deleteUser = function(user){
        if(popupService.showPopup('Really delete this?')){
            user.$delete(function(){
                $window.location.href='';
                $location.path('/users');
            });
        }

    }

});

app.controller('AdminListController',function($scope,$state,$location,$stateParams,Api){

    $scope.users = Api.Diner.query();

    $scope.addPayment = function(){
        $scope.user.$save(function(){
            $location.path('/users');
        });
    }

});



app.controller('UserSuggestController',function($scope,$state,$stateParams,Api) {
    $scope.people =  Api.User.query();

});
