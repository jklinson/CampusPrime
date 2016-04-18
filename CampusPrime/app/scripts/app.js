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
    'mwl.calendar',
    'ui.bootstrap'
  ])
  .config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.when('/dashboard', '/dashboard/news');
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
      .state('forgot', {
        url: '/forgot',
        parent: 'base',
        templateUrl: 'views/forgotPassword.html',
        controller: 'ForgotPaswrdCntrl'
      })      
      .state('admin', {
        url: '/admin',
        parent: 'base',
        templateUrl: 'views/admin/adminBase.html'
      })
      .state('dashboard', {
        url: '/dashboard',
        parent: 'base',
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardCtrl'
      })
      .state('news', {
        url: '/news',
        parent: 'dashboard',
        templateUrl: 'views/dashboard/news.html',
        controller: 'NewsCtrl'
      })
      .state('writeups', {
        url: '/writeups',
        parent: 'dashboard',
        templateUrl: 'views/dashboard/writeups.html',
        controller: 'WriteupCtrl'
      })
      .state('notification', {
        url: '/notification',
        parent: 'dashboard',
        templateUrl: 'views/dashboard/notification.html',
        controller: 'NotificationCtrl'
      })
      .state('calendar', {
        url: '/calendar',
        parent: 'dashboard',
        templateUrl: 'views/dashboard/calendar.html',
        controller: 'CalendarCtrl'
      });
  });
