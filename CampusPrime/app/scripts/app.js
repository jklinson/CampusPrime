'use strict';

/**
 * @ngdoc overview
 * @name campusPrime
 * @description
 * # campusPrime
 *
 * Main module of the application.
 */
angular
  .module('campusPrime', [
    'ui.router',
    'ngAnimate',
    'ngCookies',
    'ui.bootstrap'
  ])
  .config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.when('/dashboard', '/dashboard/overview');
    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('base', {
        abstract: true,
        url: '',
        templateUrl: 'views/base.html'
      })
        .state('login', {
          url: '/login',
          parent: 'base',
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl'
        })
        .state('register', {
          url: '/register',
          parent: 'base',
          templateUrl: 'views/register.html',
          controller: 'RegisterCtrl'
        })
        .state('dashboard', {
          url: '/dashboard',
          parent: 'base',
          templateUrl: 'views/dashboard.html',
          controller: 'DashboardCtrl'
        })
          .state('overview', {
            url: '/overview',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/news.html',
            controller: 'NewsCtrl'
          })
          .state('reports', {
            url: '/reports',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/writeups.html'
          });

  });
