/**
 * 
 */
'use strict'

myApp.controller('RequestController', [ '$scope', 'RequestService',
		'$location', '$rootScope',
		function($scope, RequestService, $location,$rootScope) {
			console.log("Request Controller...")
			var self = this;

			self.user = {
				user_id : '',
				user_name : '',
				password : '',
				firstname : '',
				lastname : '',
				contact : '',

				email : '',
				role : '',

				isOnline : '',
				errorCode : '',
				errorMessage : ''

			};
			self.users = [];

			self.friend = {
				id : '',
				friend_id : '',
				user_id : '',
				friendname : '',
				name : '',
				request_status : '',
				isOnline : '',
				errorCode : '',
				errorMessage : ''
			};
			self.friends = [];

			self.fetchAllUsers = function() {
				RequestService.fetchAllUsers().then(function(d) {
					console.log('Calling users from Request Controller')
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};

			self.fetchAllUsers();

			/* Sending friend Request */
			self.sendFriendRequest = sendFriendRequest

			function sendFriendRequest(friendID) {
				console.log("send friend request:" + friendID)
				RequestService.sendFriendRequest(friendID).then(function(d) {
					self.friend = d;
					alert("Friend Request sent")
				}, function(errResponse) {
					console.log("Error while sending Request Request");
				});
			}
			;
		} ]);