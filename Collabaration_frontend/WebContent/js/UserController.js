/**
 * 
 */


myApp.controller('UserController',
	function($scope,UserService,$location,$rootScope,$cookieStore){
		//console.log("UserController...")
		$scope.user={}
		$scope.registerUser=function(){
			
			var userformdata=UserService.profilePic($scope.user);
			
			
			UserService.registerUser(userformdata).then(
					function(response){
						$scope.message="Registered succesfully.. please login"
							$location.path('/login')
					},function(response){
						console.log(response.status)
						console.log(response.data)
						$scope.error=response.data
						$location.path('/register')
					})
		}
		
		$scope.fetchAllUsers=function(){
			UserService.fetchAllUsers().then(function(d){
				$scope.user=d;
				console.log("get all users in user controller.js ...")
			},
			function(errResponse){
				console.error('Error while fetching users')
			});
		};
		$scope.fetchAllUsers();
		
		$scope.isValidUser=function(){
			UserService.isValidUser($scope.user).then(function(response){
				console.log(response.data)
				$rootScope.currentUser=response.data
				$cookieStore.put("currentUser",response.data)
				$location.path('/home')
			}
			,function(response){
				$scope.error=response.data
				console.log(response.data)
				$location.path('/login')
			})
		}
		
		$rootScope.logout=function(){
			UserService.logout().then(function(response){
				
				$rootScope.logoutSuccess="LoggedOut Succesfully..."
					delete $rootScope.currentUser
				$cookieStore.remove("currentUser")
				$location.path('/login')
			}
			,function(response){
				$scope.error=response.data
				$location.path('/login')
			})
		}
		$scope.updateUser=function(){
			UserService.updateUser($rootScope.currentUser).then(function(response){
				alert("updated successfully")
				$location.path('/home')
			},function(response){
				console.log(response.data)
				
				if(response.status==401)
					$location.path('/login')
					$location.path('/updateprofile')
			})
		}
})

myApp.directive('fileModel',['$parse',function ($parse){
	return {
		restrict:'A',
		link:function (scope,element,attrs){
			var model=$parse(attrs.fileModel);
			var modelSetter=model.assign;
			
			element.bind('change',function(){
				scope.$apply(function(){
					modelSetter(scope,element[0].files[0]);
				});
			});
		}
	};
}]);
		