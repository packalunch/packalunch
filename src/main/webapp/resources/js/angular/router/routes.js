/**
 * Created by Sadra on 3/18/15.
 */
app.config(function($stateProvider) {
        $stateProvider
        .state('welcome',{
            url:'',
            templateUrl:'partials/theme/_home.html',
            controller:'HomeController',
            data: {
                requireLogin: false
            }
        })
        //.state('home',{
        //    url:'/home',
        //    templateUrl:'partials/home.html',
        //    controller:'HomeController',
        //    data: {
        //        requireLogin: false
        //    }
        //})
        .state('app', {
            abstract: true,
            data: {
                requireLogin: true // this property will apply to all children of 'app'
            }
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
        //.state('login',{
        //    url:'/login',
        //    templateUrl:'partials/login.html',
        //    controller:'LoginController'
        //})

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
        })
        .state('registerUser',{
            url:'/signup',
            templateUrl:'partials/registration.html',
            controller:'RegistrationController'
        });
    });