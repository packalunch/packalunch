'use strict';

angular.module('userApp',['ui.router','ngResource','userApp.controllers','userApp.services', 'ngTouch', 'angucomplete']);



angular.module('userApp').config(function($stateProvider,$httpProvider){
    $stateProvider.state('adminView',{
        url:'/admin',
        templateUrl:'partials/admin_view.html',
        controller:'AdminListController'
    }).state('users',{
        url:'/users',
        templateUrl:'partials/customer_list.html',
        controller:'UserListController'
    }).state('login',{
        url:'/login',
        templateUrl:'partials/login.html',
        controller:'LoginController'
    }).state('home',{
        url:'/home',
        templateUrl:'partials/home.html',
        controller:'HomeController'
    }).state('viewUser',{
        url:'/users/:id/view',
        templateUrl:'partials/customer_view.html',
        controller:'UserViewController'
    }).state('newUser',{
        url:'/users/new',
        templateUrl:'partials/customer_add.html',
        controller:'UserCreateController'
    }).state('editUser',{
        url:'/users/:id/edit',
        templateUrl:'partials/customer_edit.html',
        controller:'UserEditController'
    }).state('registerUser',{
        url:'/registerUser',
        templateUrl:'partials/registration.html',
        controller:'RegistrationController'
    });
}).run(function($state){
    $state.go('users');
});