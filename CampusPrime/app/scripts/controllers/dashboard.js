'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
    .controller('DashboardCtrl', function($scope, $rootScope, $state, $cookieStore, UserService, $location) {

        $scope.$state = $state;
        /**
         * Sidebar Toggle & Cookie Control
         */
        var mobileView = 992;

        $scope.getWidth = function() {
            return window.innerWidth;
        };

        $scope.$watch($scope.getWidth, function(newValue, oldValue) {
            if (newValue >= mobileView) {
                if (angular.isDefined($cookieStore.get('toggle'))) {
                    $scope.toggle = !$cookieStore.get('toggle') ? false : true;
                } else {
                    $scope.toggle = true;
                }
            } else {
                $scope.toggle = false;
            }

        });

        $scope.toggleSidebar = function() {
            $scope.toggle = !$scope.toggle;
            $cookieStore.put('toggle', $scope.toggle);
        };

        window.onresize = function() {
            $scope.$apply();
        };
        
        $rootScope.isAdmin = UserService.isAdmin();
        
        $scope.logout = function(){
            localStorage.clear();
            $location.path('/login');
        }

    });