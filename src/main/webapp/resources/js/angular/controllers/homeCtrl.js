/**
 * Created by sonic on 3/18/15.
 */

app.controller('HomeController',function($scope,$stateParams,Api){

    $scope.weekList = Api.Home.query();

});