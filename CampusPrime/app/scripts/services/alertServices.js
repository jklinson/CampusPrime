angular.module('campusPrime').service('AlertService', function($rootScope){
   
   this.showAlert = function(title, message) {

   	  $rootScope.alertTitle = title;
   	  $rootScope.alertMessage = message;
      $('#alertbox').modal('show');
   }

});