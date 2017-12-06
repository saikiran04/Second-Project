var myApp=angular.module('myApp',['ngRoute','ngCookies'])

myApp.config(function($routeProvider){
	$routeProvider
	
	
	.when('/home',{
		templateUrl:'c_home/home.html',
	   
	})
	.when('/login',{
		templateUrl:'c_user/login.html',
		controller:'UserController'
	})
	.when('/register',{
		templateUrl:'c_user/register.html',
		controller:'UserController'
	})
	.when('/updateprofile',{
		templateUrl:'c_user/updateprofile.html',
		controller:'UserController'
	})
	.when('/blog',{
		templateUrl:'c_user/blog.html',
		controller:'BlogController'
	})
	.when('/job',{
		templateUrl:'c_user/job.html',
		controller:'JobController'
	})
	.when('/getalljobs',{
		templateUrl:'c_user/jobtitles.html',
		controller:'JobController'
	})
	
	.when('/save',{
		templateUrl:'c_user/job.html',
		controller:'JobController'
	})
	.otherwise({
		templateUrl:'c_home/home.html'
		})
	
})
myApp.run(function($rootScope,$cookieStore){
	if($rootScope.currentUser=undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
})

