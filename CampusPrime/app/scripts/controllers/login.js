'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
  .controller('LoginCtrl', function($scope, $location) {

    $scope.submit = function() {

      $location.path('/dashboard');

      return false;
    }

  });
