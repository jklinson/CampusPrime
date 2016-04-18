'use strict';

/**
 * @ngdoc function
 * @name campPrime.controller:AdminCntrl
 * @description
 * # AdminCntrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
	.controller('AdminCntrl', function($scope, $location, $http, AlertService, AudienceService, UserService) {
        
       
        
        $scope.tabs = [
            {heading:"Users", template : "views/admin/users.html"},
            {heading:"News", template : "views/admin/newsAdmin.html"},
            {heading:"Write-ups", template : "views/admin/writeUpsAdmin.html"},
            {heading:"Notifications", template : "views/admin/notificationAdmin.html"},
            {heading:"Calendar", template : "views/admin/calendarAdmin.html"},
            ];
        $scope.isTeacher = UserService.getIsTeacher();
        if(!$scope.isTeacher){
            $scope.tabs.shift();
        }   
        
    });