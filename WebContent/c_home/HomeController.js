/**
 * 
 */
'use strict';
myApp.controller('HomeController',['$scope','UserService','@rootScope',
									function($scope,UserSevice,$rootScope){
	
	console.log('HomeController........')
	var self=this;
	
	self.user={
			user_id='',
			user_name='',
			password='',
			contact='',
			email='',
			role='',
			isOnline='',
			errorMessage='',
	};
	self.users=[];
	
	
	self.getCurrentUser=function(){
		console.log("Loading current user if already logged in")
		console.log("Current user: " +$rootScope.currentUser)
		if(!$rootScope.currentUser){
			console.log("User not loggen in")
			$rootScope.currentUser="";
			
		}
		return $rootScope.currentUser;
	}
	self.getCurrentUser();
	
}])