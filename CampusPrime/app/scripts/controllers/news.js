'use strict';

/**
 * @ngdoc function
 * @name campusPrime.controller:NewsCtrl
 * @description
 * # NewsCtrl
 * Controller of campusPrime
 */
angular.module('campusPrime')
  .controller('NewsCtrl', function($scope, $location) {

    $scope.myInterval	=	5000;

    $scope.news 		= 	[];

    $scope.fetchNews	= 	function(){

    	for (var i = 0; i < 5; i++) {
    		
    		var news 			=	{};
    		news.title			=	"Title "+i;
    		news.description	=	"description "+i;
    		news.publishedBy	=	"name "+i;
    		news.date			=	"12-08-2016";
    		news.id				=	i;
    		$scope.news.push(news);
    	};
    }

    $scope.fetchNews();

  });
