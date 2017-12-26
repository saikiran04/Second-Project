/**
 * JobService
 */

myApp.factory('JobService',function($http){
	var jobService={}
	
	jobService.save=function(job){
	  return  $http.post("http://localhost:8092/collabarationbackend/saveJob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8092/collabarationbackend/getalljobs")
	}
	return jobService;
})