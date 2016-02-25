'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('LoginCtrl', function($scope, $location) {

		$scope.submit = function() {

			$location.path('/dashboard');

			return false;
		}

	});