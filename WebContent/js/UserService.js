/**
 * 
 */
myApp.factory("UserService",function($http){
    var BASE_URL="http://localhost:8092/collabarationbackend";
	var userService ={};
	
	userService.profilePic=function(data)
	{
		console.log('data in multipart file service:::'+data.user_name);
		var fd=new FormData();
		for(var key in data)
			{
			console.log(key+":::"+data[key]);
			fd.append(key,data[key]);
			}
		console.log('form data'+fd);
		console.log(fd.companyLogo);
		return fd;
	}
   	userService.registerUser=function(user){
		return $http.post(BASE_URL+"/fileUpload",user,{
			transformRequest:angular.indentity,
			headers:{'Content-Type':undefined}
		});
	}
	userService.isValidUser=function(user){
		return $http.post(BASE_URL+"/login",user)
}
	userService.logout=function(){
		return $http.get(BASE_URL+"/logout")
}
	userService.updateUser=function(user){
		return $http.put(BASE_URL+"/updateuser",user)
}
	userService.fetchAllUsers=function(){
		return $http.get(BASE_URL+"/users")
	}
	
	return userService;
})