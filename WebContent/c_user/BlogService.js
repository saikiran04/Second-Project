/**
 * 
 */

myApp.factory('BlogService',function($http){
	
	var BASE_URL="http://localhost:8092/collabarationbackend";
	var blogService={};
	blogService.createBlog=function(blog){
		return $http.post(BASE_URL+"/blog",blog)
	}
	
	return blogService;
})