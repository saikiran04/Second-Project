/**
 * 
 */
'use strict';

myApp.factory('RequestService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	
	console.log("Request Service..")
	
	 var BASE_URL='http://localhost:8092/collabarationbackend'
		return{
		
		fetchAllUsers:function(){
			return $http.get(BASE_URL+'/users')
			.then(
					//success handler
					function(response){
						console.log('calling user in user service...')
						return response.data;
					},//failure handler
					function(errResponse){
						console.error('Error whilr fetching user details..')
						return $q.reject(errResponse);
					});
		},
	/*Send Friend Request*/
		sendFriendRequest:function(id){
			return $http.delete(BASE_URL+'/addFriend/',id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						 console.error('Error while sending friend request');
						 return $q.reject(errResponse);
					 });
		},
	
}
	
}])