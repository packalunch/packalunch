/**
* Created by Sadra on 3/29/15.
*/

app.service('userMealService', ['$state', '$location','authService', 'Api', function ( $state,$location,authService, Api) {

    this.saveMealOrder = function (weekOrder) {
        var dinerMeal = new Api.DinerMeal();
        dinerMeal.id = authService.getCurrentUser().id;
        dinerMeal.dinerSchedule = weekOrder;
        console.log ('add meal ctl user:');
        console.log (dinerMeal);

        dinerMeal.$save(function(){
            console.log ('meals saved. changing path to /user');
            $location.path('/user');
        });
    }
    }]
);