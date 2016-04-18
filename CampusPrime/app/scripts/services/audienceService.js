angular.module('campusPrime').service('AudienceService', function($rootScope,$http){
   
       this.yearClassList = null;
       
       this.getYearAncClasses	= function(scb, ecb) {
           
           if(this.yearClassList === null){
               $http({
			        method: 'GET',
			        url: 'http://localhost:8080/RESTfulProject/REST/WebService/GetYearAndClass'

			    }).then(function successCallback(response) {
			        console.log('In successCallback '+JSON.stringify(response));
                    if(response.data.status === Constants.success){
                        this.yearClassList = JSON.parse(response.data.yearClassList);
                        scb(this.yearClassList);
                    }else{
                        ecb(response.data.message);
                        AlertService.showAlert("Campus Prime", response.data.message + "\n Please try  again later.");
                    }
                }, function errorCallback(response) {
                    console.log('In errorCallback '+JSON.stringify(response));
                    ecb(response);
                });
           }
           else{
               scb(this.yearClassList);
           }
			
		}

});