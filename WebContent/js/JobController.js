/**
 * Job Controller
 */
myApp.controller('JobController',function(JobService,$scope,$location){
	
	
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data;
		},function(response){
			$location.path('/login')
		})
	}
	
	$scope.save=function(){
		JobService.save($scope.job).then(function(response){
			$location.path('/getalljobs')
		},function(response){
			console.log(response.status)
			if(response.status==401){
				$scope.error=response.data
				alert("Access Denied. Only Admin can add Jobs.")
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
				$location.path('/save')
			}
			$location.path('/home')
		})
	}
getAllJobs()	
	
})