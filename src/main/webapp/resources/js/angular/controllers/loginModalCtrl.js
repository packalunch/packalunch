/**
* Created by Sadra on 3/18/15.
*/

app.controller('LoginModalCtrl', function ($rootScope, $scope, $http, $location,$modalInstance) {

    var authenticate = function(callback) {

        $http.get('auth/username').success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
                $modalInstance.close(data.principal);
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    authenticate();

    $scope.credentials = {};

    $scope.login = function() {
        console.log($scope.credentials);
        $http.post('login', $.param($scope.credentials), {
            headers : {
                "content-type" : "application/x-www-form-urlencoded"
            }
        }).success(function(data) {
            authenticate(function() {
                if ($rootScope.authenticated) {
                    console.log("Login succeeded");
                    $location.path("/");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed with redirect");
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            });
        }).error(function(data) {
            console.log("Login failed");
            $location.path("/login");
            $scope.error = true;
            $rootScope.authenticated = false;
        })
    };

    $scope.logout = function () {
        $http.post('logout', {}).success(function () {
            $rootScope.authenticated = false;
            delete $rootScope.currentUser;//TODO: Test
            $location.path("/");
        }).error(function (data) {
            console.log("Logout failed");
            $rootScope.authenticated = false;
            delete $rootScope.currentUser;
        });
    };

});



