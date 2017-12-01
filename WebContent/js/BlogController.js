/**
 * BlogController
 */
myApp.controller('BlogController',function(BlogService,$scope,$location){
	$scope.createBlog=function(){
		BlogService.createBlog($scope.blog).then(function(response){
			console.log(response.status)
			alert('BlogPost added successfully.. It is waiting for approval')
			$location.path('/blog')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$location.path('/blog')
		})
	}
})