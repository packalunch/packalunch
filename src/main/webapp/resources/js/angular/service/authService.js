/**
* Created by Sadra on 3/11/15.
*/

app.service('authService', ['$rootScope', 'loginModal', function ($rootScope, loginModal) {

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
                    return $state.go('home');
                });
        }
    };

}]
);