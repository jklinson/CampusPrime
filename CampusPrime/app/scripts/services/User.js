angular.module('campusPrime').service('UserService', function($rootScope, $location){
   
   var user = {};
   this.setUser = function(logedInUser) {
       if(localStorage.getItem("rememberMe")==="true"){
           localStorage.setItem("user",JSON.stringify(logedInUser));
       }
   	  user = logedInUser;
   }

   this.getUser = function() {
       
       if(localStorage.getItem("rememberMe") === "true"){
           user = JSON.parse(localStorage.getItem("user"));
       }
       else{
           if(user ==={}){
               $location.path('/login');
           }
       }
   	  return user;
   }
   this.getUserId = function() {
       user = this.getUser();
   	  return user.userId;
   }
   this.getUserBatch = function() {
       user = this.getUser();
   	  return user.year +' - '+ (parseInt(user.year)+4);
   }
   this.getUserYear = function() {
       user = this.getUser();
   	  return user.year;
   }
   this.getUserClass = function() {
        user = this.getUser();
   	  return user.classOrSRoom;
   }
   this.isAdmin = function(){
        user = this.getUser();
       return user.isAdmin;
   }
   this.getIsTeacher = function() {
       user = this.getUser();
       return user.isTeacher;
   }

});