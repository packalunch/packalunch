/**
* Created by Sadra on 3/29/15.
*/

app.controller('UserAddMealCtrl', function($scope,$state,$stateParams,authService,userMealService,Api) {

    $scope.addDinerMeal = function () {
        console.log ('add meal ctl user:-----------addDinerMeal');

        if (authService.isLoggedIn()){
            console.log("in isLoggedIN");

            userMealService.saveMealOrder ($scope.weekList);

            $scope.alerts = [
                { type: 'success', msg: 'Chill, we will serve you.' }
            ];

        } else {
            authService.promptLogin();
            console.log("in FAILED isLoggedIN");
        }



    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

});

app.controller('UserMealCtrl', function($scope,authService,Api) {
    if ( authService.getCurrentUser()) {
        $scope.user = Api.Diner.get( {id : authService.getCurrentUser().id} );
    }

});

