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

