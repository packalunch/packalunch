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
            templateUrl:'partials/theme/_home.html',
            controller:'HomeController'
        })
        .state('public.registerUser',{
            url:'/socialsignup',
            templateUrl:'partials/socialRegistration.html',
            controller:'RegistrationController'
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