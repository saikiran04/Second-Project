/**
 * 
 */


myApp.controller('UserController',
	function($scope,UserService,$location,$rootScope,$cookieStore){
		//console.log("UserController...")
		$scope.user={}
		$scope.registerUser=function(){
			UserService.registerUser($scope.user).then(
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

		/*self.users=[];
		
		self.friend={
				id:'',
				friend_id:'',
				friend_name:'',
				user_id:'',
				request_status:'',
				isOnline:'',
				errorCode:'',
				errorMessage:''
				};
		self.friends=[];
Fetch All Users
		
		self.fetchAllUsers=function(){
			UserService.fetchAllUsers().then(function(d){
				self.users=d;
			},function(errResponse){
				console.error('Error while fetching users');
			});
		};
		self.fetchAllUsers();
		
		Create users
		
		self.createUser=function(user){
			UserService.createUser(user)
			.then(self.fetchAllUsers,
					function(errResponse){
				console.error('Error while creating user..');
			});
		};
	Update User	
		self.createUser=function(user){
			UserService.updateUser(user)
			.then(self.fetchAllUsers,
					function(errResponse){
				console.error('Error while updating user..');
			});
		};
Authentication of user
		self.authenticate=function(user){
			UserService.authenticate(user)
			.then(function(d){
				self.user=d;
				if(self.user.email=="admin@gmail.com"&&self.user.password=="admin"){
					$location.path('/admin')
				}
				else if($rrotScope.currentUser){
					$location.path('/');
				}
			},function(errResponse){
				console.error('Error while authenticating user..');
			});
		};
		
		//Delete User
		self.deleteUser=function(user){
			UserService.createUser(user)
			.then(self.fetchAllUsers,
					function(errResponse){
				console.error('Error while deleting user..');
			});
			self.login=function(){
				{
					console.log('login validation????',self.user);
					self.authenticate(self.user);
				}
			};
			self.logout=function(){
				console.log('Logging out');
				UserServices.logout()
				.then(function(){
					if(self.errorCode==200){
						console.log("You have successfully logged out!");
						alert("you have succesfully logged out");
					}
				},
				function(errResponse){
					console.error('Error while logging out');
				})
			};
			self.submit=function(){
				console.log('saving New user',self.user);
				self.createUser(self.user);
				
				self.reset();
				
			};
			self.reset=function(){
				console.log('reset user',self.user);
				self.user={
						user_id:'',
						user_name:'',
						password:'',
						cpassword:'',
						contact:'',
						address:'',
						email:'',
						role:'',
						image:'',
						isOnline:'',
						errorCode:'',
						errorMessage:''
						
				};
			};
		};
	}*/
