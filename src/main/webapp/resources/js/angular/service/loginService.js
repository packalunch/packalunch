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