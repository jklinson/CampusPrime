'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:WriteupCtrl
 * @description
 * # WriteupCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('WriteupCtrl', function($scope, $rootScope, $location) {

		$rootScope.currentPage = 'Write-Ups';
		$scope.writeups = [];

		$scope.fetchWriteUps = function() {

			for (var i = 0; i < 5; i++) {
				var writeup = {};
				writeup.img = 'images/sngce_emblom.jpg';
				writeup.title = 'Write up titles ' + i;
				writeup.description = 'Write up descriptions go on ' + i;
				writeup.clapCount = 100 + i;
				$scope.writeups.push(writeup);
			};
		}

		$scope.fetchWriteUps();

	});