'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:CalendarCtrl
 * @description
 * # CalendarCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('CalendarCtrl', function($scope, $rootScope, $location, moment) {

		$rootScope.currentPage = 'Calendar';
		$scope.calendarView = 'month';
	    $scope.viewDate = new Date();
	    $scope.events = [
	      {
	        title: 'An event',
	        type: 'warning',
	        startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
	        endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
	        draggable: true,
	        resizable: true
	      }, {
	        title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
	        type: 'info',
	        startsAt: moment().subtract(1, 'day').toDate(),
	        endsAt: moment().add(5, 'days').toDate(),
	        draggable: true,
	        resizable: true
	      }, {
	        title: 'This is a really long event title that occurs on every year',
	        type: 'important',
	        startsAt: moment().startOf('day').add(7, 'hours').toDate(),
	        endsAt: moment().startOf('day').add(19, 'hours').toDate(),
	        recursOn: 'year',
	        draggable: true,
	        resizable: true
	      }
	    ];

	    $scope.isCellOpen = true;

	    $scope.eventClicked = function(event) {
	      alert('Clicked', event);
	    };

	    $scope.eventEdited = function(event) {
	      alert('Edited', event);
	    };

	    $scope.eventDeleted = function(event) {
	      alert('Deleted', event);
	    };

	    $scope.eventTimesChanged = function(event) {
	      alert('Dropped or resized', event);
	    };
	});