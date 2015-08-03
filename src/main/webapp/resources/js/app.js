'use strict';

var app = angular.module('userApp', ['ui.router','ngResource',
    'ui.bootstrap', 'ngTouch', 'spring-security-csrf-token-interceptor']);

app.config(function ($httpProvider) {

    $httpProvider.interceptors.push(function ($timeout, $q, $injector) {
        var loginModal, $http, $state;

        // this trick must be done so that we don't receive
        // `Uncaught Error: [$injector:cdep] Circular dependency found`
        $timeout(function () {
            loginModal = $injector.get('loginModal');
            $http = $injector.get('$http');
            $state = $injector.get('$state');
        });

        return {
            responseError: function (rejection) {
                if (rejection.status !== 401) {
                    return rejection;
                }

                var deferred = $q.defer();

                loginModal()
                    .then(function () {
                        deferred.resolve( $http(rejection.config) );
                    })
                    .catch(function () {
                        $state.go('home');
                        deferred.reject(rejection);
                    });

                return deferred.promise;
            }
        };
    });

});

app.run(function ($rootScope, $state, loginModal) {

    $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
        var requireLogin = toState.data.requireLogin;
        console.log('in state change start-------------');
        console.log(requireLogin);
        console.log($rootScope.currentUser);
        console.log('in state change end-------------');
        if (requireLogin && typeof $rootScope.currentUser === 'undefined') {
            event.preventDefault();

            loginModal()
                .then(function () {
                    return $state.go(toState.name, toParams);
                })
                .catch(function () {
                    console.log('in catch state change start-------------');
                    return $state.go('home');
                });
        }
        console.log('AFTER');
    });

});


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

/**
 * Created by sadra on 3/18/15.
 */

app.controller('HomeController',function($scope,$stateParams,Api){

    $scope.weekList = Api.Home.query();

});
/**
* Created by Sadra on 3/18/15.
*/

app.controller('LoginModalCtrl', function ($rootScope, $scope, $http, $location, $modalInstance) {

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
        console.log("posting to login with user credentials");
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
            console.log("Logout successful");
            $rootScope.authenticated = false;
            delete $rootScope.currentUser;
            $location.path("/");
        }).error(function (data) {
            console.log("Logout failed");
            $rootScope.authenticated = false;
            delete $rootScope.currentUser;
        });
    };

});




/**
* Created by Sadra on 3/29/15.
*/

app.controller('navCtrl',function($scope,authService){



});
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
/**
* PackALunch
* Created by Sadra on 3/28/15.
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

/**
* Created by Sadra on 3/29/15.
*/

app.controller('UserAddMealCtrl', function($scope,$state,$stateParams,authService,userMealService) {

    $scope.addDinerMeal = function () {
        console.log ('add meal ctl user:-----------addDinerMeal');

        if (authService.isLoggedIn()){
            console.log("in isLoggedIN");

            userMealService.saveMealOrder ($scope.weekList);

            $scope.alerts = [{ type: 'success', msg: 'Chill, we will serve you.' }];

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
        console.log("UserMeal Controller ==========="  + authService.getCurrentUser().id );
        $scope.user = Api.Diner.get( {id : authService.getCurrentUser().id} );
    }

});


/**
 * Created by sadra on 1/1/15.
 * Angucomplete
 * Autocomplete directive for AngularJS
 * By Daryl Rowland
 */
//.module('angucomplete', [] )
app.directive('angucomplete', function ($parse, $http, $sce, $timeout, userProperties) {
        return {
            restrict: 'EA',
            scope: {
                "id": "@id",
                "placeholder": "@placeholder",
                "selectedObject": "=selectedobject",
                "url": "@url",
                "dataField": "@datafield",
                "titleField": "@titlefield",
                "descriptionField": "@descriptionfield",
                "imageField": "@imagefield",
                "imageUri": "@imageuri",
                "inputClass": "@inputclass",
                "userPause": "@pause",
                "localData": "=localdata",
                "searchFields": "@searchfields",
                "minLengthUser": "@minlength",
                "matchClass": "@matchclass"
            },
            template: '<div class="angucomplete-holder"><input id="{{id}}_value" ng-model="searchStr" type="text" placeholder="{{placeholder}}" class="{{inputClass}}" onmouseup="this.select();" ng-focus="resetHideResults()" ng-blur="hideResults()" /><div id="{{id}}_dropdown" class="angucomplete-dropdown" ng-if="showDropdown"><div class="angucomplete-searching" ng-show="searching">Searching...</div><div class="angucomplete-searching" ng-show="!searching && (!results || results.length == 0)">No results found</div><div class="angucomplete-row" ng-repeat="result in results" ng-click="selectResult(result)" ng-mouseover="hoverRow()" ng-class="{\'angucomplete-selected-row\': $index == currentIndex}"><div ng-if="imageField" class="angucomplete-image-holder"><img ng-if="result.image && result.image != \'\'" ng-src="{{result.image}}" class="angucomplete-image"/><div ng-if="!result.image && result.image != \'\'" class="angucomplete-image-default"></div></div><div class="angucomplete-title" ng-if="matchClass" ng-bind-html="result.title"></div><div class="angucomplete-title" ng-if="!matchClass">{{ result.title }}</div><div ng-if="result.description && result.description != \'\'" class="angucomplete-description">{{result.description}}</div></div></div></div>',

            link: function($scope, elem, attrs) {
                $scope.lastSearchTerm = null;
                $scope.currentIndex = null;
                $scope.justChanged = false;
                $scope.searchTimer = null;
                $scope.hideTimer = null;
                $scope.searching = false;
                $scope.pause = 500;
                $scope.minLength = 3;
                $scope.searchStr = null;

                if ($scope.minLengthUser && $scope.minLengthUser != "") {
                    $scope.minLength = $scope.minLengthUser;
                }

                if ($scope.userPause) {
                    $scope.pause = $scope.userPause;
                }

                isNewSearchNeeded = function(newTerm, oldTerm) {
                    return newTerm.length >= $scope.minLength && newTerm != oldTerm
                }

                $scope.processResults = function(responseData, str) {
                    if (responseData && responseData.length > 0) {
                        $scope.results = [];

                        var titleFields = [];
                        if ($scope.titleField && $scope.titleField != "") {
                            titleFields = $scope.titleField.split(",");
                        }

                        for (var i = 0; i < responseData.length; i++) {
                            // Get title variables
                            var titleCode = [];

                            for (var t = 0; t < titleFields.length; t++) {
                                titleCode.push(responseData[i][titleFields[t]]);
                            }

                            var description = "";
                            if ($scope.descriptionField) {
                                description = responseData[i][$scope.descriptionField];
                            }

                            var imageUri = "";
                            if ($scope.imageUri) {
                                imageUri = $scope.imageUri;
                            }

                            var image = "";
                            if ($scope.imageField) {
                                image = imageUri + responseData[i][$scope.imageField];
                            }

                            var text = titleCode.join(' ');
                            if ($scope.matchClass) {
                                var re = new RegExp(str, 'i');
                                var strPart = text.match(re)[0];
                                text = $sce.trustAsHtml(text.replace(re, '<span class="'+ $scope.matchClass +'">'+ strPart +'</span>'));
                            }

                            var resultRow = {
                                title: text,
                                description: description,
                                image: image,
                                originalObject: responseData[i]
                            }

                            $scope.results[$scope.results.length] = resultRow;
                        }


                    } else {
                        $scope.results = [];
                    }
                }

                $scope.searchTimerComplete = function(str) {
                    // Begin the search

                    if (str.length >= $scope.minLength) {
                        if ($scope.localData) {
                            var searchFields = $scope.searchFields.split(",");

                            var matches = [];

                            for (var i = 0; i < $scope.localData.length; i++) {
                                var match = false;

                                for (var s = 0; s < searchFields.length; s++) {
                                    match = match || (typeof $scope.localData[i][searchFields[s]] === 'string' && typeof str === 'string' && $scope.localData[i][searchFields[s]].toLowerCase().indexOf(str.toLowerCase()) >= 0);
                                }

                                if (match) {
                                    matches[matches.length] = $scope.localData[i];
                                }
                            }

                            $scope.searching = false;
                            $scope.processResults(matches, str);

                        } else {
                            $http.get($scope.url + str, {}).
                                success(function(responseData, status, headers, config) {
                                    $scope.searching = false;
                                    $scope.processResults((($scope.dataField) ? responseData[$scope.dataField] : responseData ), str);
                                }).
                                error(function(data, status, headers, config) {
                                    console.log("error");
                                });
                        }
                    }
                }

                $scope.hideResults = function() {
                    $scope.hideTimer = $timeout(function() {
                        $scope.showDropdown = false;
                    }, $scope.pause);
                };

                $scope.resetHideResults = function() {
                    if($scope.hideTimer) {
                        $timeout.cancel($scope.hideTimer);
                    };
                };

                $scope.hoverRow = function(index) {
                    $scope.currentIndex = index;
                }

                $scope.keyPressed = function(event) {
                    if (!(event.which == 38 || event.which == 40 || event.which == 13)) {
                        if (!$scope.searchStr || $scope.searchStr == "") {
                            $scope.showDropdown = false;
                            $scope.lastSearchTerm = null
                        } else if (isNewSearchNeeded($scope.searchStr, $scope.lastSearchTerm)) {
                            $scope.lastSearchTerm = $scope.searchStr
                            $scope.showDropdown = true;
                            $scope.currentIndex = -1;
                            $scope.results = [];

                            if ($scope.searchTimer) {
                                $timeout.cancel($scope.searchTimer);
                            }

                            $scope.searching = true;

                            $scope.searchTimer = $timeout(function() {
                                $scope.searchTimerComplete($scope.searchStr);
                            }, $scope.pause);
                        }
                    } else {
                        event.preventDefault();
                    }
                }

                $scope.selectResult = function(result) {
                    if ($scope.matchClass) {
                        result.title = result.title.toString().replace(/(<([^>]+)>)/ig, '');
                    }
                    $scope.searchStr = $scope.lastSearchTerm = result.title;
                    $scope.selectedObject = result;
                    userProperties.setUser($scope.selectedObject);
                    $scope.showDropdown = false;
                    $scope.results = [];
                    //$scope.$apply();
                }

                var inputField = elem.find('input');

                inputField.on('keyup', $scope.keyPressed);

                elem.on("keyup", function (event) {
                    if(event.which === 40) {
                        if ($scope.results && ($scope.currentIndex + 1) < $scope.results.length) {
                            $scope.currentIndex ++;
                            $scope.$apply();
                            event.preventDefault;
                            event.stopPropagation();
                        }

                        $scope.$apply();
                    } else if(event.which == 38) {
                        if ($scope.currentIndex >= 1) {
                            $scope.currentIndex --;
                            $scope.$apply();
                            event.preventDefault;
                            event.stopPropagation();
                        }

                    } else if (event.which == 13) {
                        if ($scope.results && $scope.currentIndex >= 0 && $scope.currentIndex < $scope.results.length) {
                            $scope.selectResult($scope.results[$scope.currentIndex]);
                            $scope.$apply();
                            event.preventDefault;
                            event.stopPropagation();
                        } else {
                            $scope.results = [];
                            $scope.$apply();
                            event.preventDefault;
                            event.stopPropagation();
                        }

                    } else if (event.which == 27) {
                        $scope.results = [];
                        $scope.showDropdown = false;
                        $scope.$apply();
                    } else if (event.which == 8) {
                        $scope.selectedObject = null;
                        userProperties.setUser($scope.selectedObject);
                        $scope.$apply();
                    }
                });

            }
        };
    });




/**
 * Created by sadra on 3/18/15.
 */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */
app.factory('Api', ['$resource',
    function($resource) {
        return {
            User: $resource('api/customer/:id', {id:'@id'}, { update: { method: 'PUT' } }),
            LocalUser: $resource('api/localRegister/:id', {id: '@id'}),
            DinerMeal: $resource('api/dinerMeal/:id', {id: '@id'}),
            Diner: $resource('api/diner/:id', {id: '@id'}),
            DinerPayment: $resource('api/diner/:id/payment', {id: '@id'}),
            UserAuthenticate: $resource('auth/username/:id', {id: '@id'}),
            SocialAuth: $resource('auth/social/:id', {id: '@id'}),
            Home: $resource('home/:id', {id: '@id'}),
            UserRegister: $resource('api/register/:id', {id: '@id'})
        };
    }]);
/**
 * Created by sadra on 3/18/15.
 */

/*
 */
app.factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){
    return {
        response: function(response){
            if (response.status === 401) {
                console.log("Response 401");
            }
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status === 401) {
                console.log("Response Error 401",rejection);
                $location.path('/login').search('returnTo', $location.path());
            }
            return $q.reject(rejection);
        }
    }
}]);
/**
* Created by Sadra on 3/18/15.
*/
app.config(function($stateProvider) {
        $stateProvider
        .state('public', {
            abstract: true,
            template: '<ui-view/>', // needed for nested templates.
            data: {
                requireLogin: false // shared.
            }
        })
        .state('app', {
            abstract: true,
            template: '<ui-view/>',
            data: {
                requireLogin: true
            }
        })
        .state('public.welcome',{
            url:'',
            templateUrl:'partials/marketing/_home.html',
            controller:'HomeController'
        })
        .state('public.socialRegisterUser',{
            url:'/socialsignup',
            templateUrl:'partials/socialRegistration.html',
            controller:'RegistrationController'
        })
        .state('public.localregister',{
            url:'/register',
            templateUrl:'partials/registration.html',
            controller:'RegistrationController'
        })
        .state('public.login',{
            url:'/login',
            templateUrl:'partials/login.html',
            controller:'LoginModalCtrl'
        })
        .state('app.user',{
            url:'/user',
            templateUrl:'partials/user_home.html',
            controller:'UserCtrl'
        })
        .state('app.adminView',{
            url:'/admin',
            templateUrl:'partials/admin_view.html',
            controller:'AdminListController'
        })
        .state('app.customerOrder',{
            url:'/customerOrder',
            templateUrl:'partials/admin_view.html',
            controller:'UserAddMealController'
        })

        // old
        .state('users',{
            url:'/users',
            templateUrl:'partials/customer_list.html',
            controller:'UserListController'
        })

        .state('viewUser',{
            url:'/users/:id/view',
            templateUrl:'partials/customer_view.html',
            controller:'UserViewController'
        })
        .state('newUser',{
            url:'/users/new',
            templateUrl:'partials/customer_add.html',
            controller:'UserCreateController'
        })
        .state('editUser',{
            url:'/users/:id/edit',
            templateUrl:'partials/customer_edit.html',
            controller:'UserEditController'
        });
    });
/**
* Created by Sadra on 3/11/15.
*/

app.service('authService', ['$rootScope', 'loginModal', '$location', function ($rootScope, loginModal, $location) {

    this.getCurrentUser = function () {
        return $rootScope.currentUser;
    };

    this.isLoggedIn = function () {
        return typeof $rootScope.currentUser !== 'undefined';
    };

    this.promptLogin = function () {
        if (typeof $rootScope.currentUser === 'undefined') {
            loginModal()
                .then(function () {
                    // nothing to do
                })
                .catch(function () {
                    //return $state.go('home');
                    $location.path('/');
                });
        }
    };

}]
);
/**
* Created by Sadra on 3/18/15.
*/

app.service('loginModal', ['$modal', '$rootScope', function ($modal, $rootScope) {

    function assignCurrentUser (user) {
        $rootScope.currentUser = user;
        console.log(user);
        return user;
    }

    return function() {
        var instance = $modal.open({
            templateUrl: 'partials/login.html',
            controller: 'LoginModalCtrl',
            controllerAs: 'LoginModalCtrl'
        });

        return instance.result.then(assignCurrentUser);
    };

}]

);


//angular.module('userApp.services',[])
//    .factory('Api', ['$resource',
//    function($resource) {
//        return {
//            User: $resource('api/customer/:id', {id:'@id'}, {
//                update: { method: 'PUT' }
//            }),
//            DinerMeal: $resource('api/dinerMeal/:id', {id: '@id'}),
//            Diner: $resource('api/diner/:id', {id: '@id'}),
//            DinerPayment: $resource('api/diner/:id/payment', {id: '@id'}),
//            UserAuthenticate: $resource('login/authenticate/:id', {id: '@id'}),
//            Home: $resource('api/home/:id', {id: '@id'})
//        };
//}])

app.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});

app.service('userProperties', function () {
    var user;
    return {
        getUser: function () {
            return user;
        },
        setUser: function(value) {
            user = value.originalObject;
        }
    };
});
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