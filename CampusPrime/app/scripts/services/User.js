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
   this.getUserBatch = function() {

   	  return user.year +' - '+ (parseInt(user.year)+4);
   }
   this.getUserYear = function() {

   	  return user.year;
   }
   this.getUserClass = function() {

   	  return user.classOrSRoom;
   }

});