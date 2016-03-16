angular.module('campusPrime').service('UserService', function($rootScope){
   
   var user = {};
   this.setUser = function(logedInUser) {

   	  user = logedInUser;
   }

   this.getUser = function() {

   	  return user;
   }
   this.getUserId = function() {

   	  return user.userId;
   }

});