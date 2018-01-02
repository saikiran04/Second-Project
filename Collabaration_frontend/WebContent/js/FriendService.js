/**
 * 
 */
'use strict';

myApp.factory('FriendService',['$http','$q','$rootScope',function($http,$q,$rootScope){
                      
console.log('FriendService...')

var BASE_URL='http://localhost:8092/collabarationbackend'
	
return{
	
	fetchAllUsers:function(){
		return $http.get(BASE_URL+'/users')
		.then(
				 //success handler
				function(response){
					console.log('Calling user in user service....... ')
					return response.data;
				},//failure handler
				function(errResponse){
					console.error('Error while fetching UserDetails');
					return $q.reject(errResponse);
				}				
);
},
	
	/*GET MY FRIENDS LIST*/
	getMyFriends:function(){
		return $http.get(BASE_URL+'/myFriends')
		.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching My Friends');
					return $q.reject(errResponse);
				}				
);
},

//SEND FRIEND REQUEST
sendFriendRequest:function(friendID){
	console.log(friendID);
	return $http.get(BASE_URL+'/addFriend/'+friendID)
	.then(
			function(response){
				console.log("in addfriend");
				return response.data;
			},
			function(errResponse){
				console.error('Error while sending Friend request');
				return $q.reject(errResponse);
			}				
);
},

/*UPDATE FRIEND REQUEST*/
updateFriend:function(friend){
	return $http.put(BASE_URL+'/updateFriend/', friend)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while updating Friend request');
				return $q.reject(errResponse);
			}				
);
},

/*DELETE FRIEND REQUEST*/
deleteFriend:function(friend){   
	return $http.delete(BASE_URL+'/deleteFriend/',friend)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while deleting Friend');
				return $q.reject(errResponse);
			}				
);
},
};

}
]
);
