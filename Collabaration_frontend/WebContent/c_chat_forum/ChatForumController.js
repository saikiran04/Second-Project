/**
 * 
 */
myApp.controller("ChatForumController",function($scope,ChatForumService){
	console.log("ChatForumController...")
	$scope.messages=[];
	$scope.message="";
	$scope.max=50;
	
	$scope.addMessage=function(){
		console.log("addMessage")
		ChatForumService.send($rootScope.currentUser.username+":" +$scope.message);
		$scope.message="";
	};
	
	ChatForumService.receive().then(null,null,function(message){  // here the success handler and failure handler - r null
		console.log("receive")
		$scope.messages.push(message);  //this msg we hv display in html txt area
	});
	});