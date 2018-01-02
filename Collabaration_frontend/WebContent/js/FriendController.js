/**
 * 
 */
'use strict'

myApp
		.controller(
				'FriendController',
				[
						'$scope',
						'FriendService',
						'$location',
						'$rootScope',
						'$http',
						function($scope, FriendService, $location, $rootScope,
								$http) {

							console.log("FriendController....")
							var self = this;
							self.friend = { // initialization
								id : '',
								friend_id : '',
								user_id : '',
								friendname:'',
								name:'',
								request_status : '',
								isOnline : '',
								errorCode : '',
								errorMessage : ''
							};
							self.friends = [];

							self.user = {
								user_id : '',
								user_name : '',
								password : '',
								firstname : '',
								lastname:'',
								contact: '',
								isOnline: '',
								email : '',
								role : '',
								errorCode : '',
								errorMessage : '',
							};
							self.users = [];

							/*
							 * SENDING FRIEND
							 * REQUEST.............................
							 */
							self.sendFriendRequest = sendFriendRequest

							function sendFriendRequest(friendID) {
								console.log("sendFriendRequest:" + friendID)
								FriendService
										.sendFriendRequest(friendID)
										.then(
												function(d) {
													self.friend = d;
													alert("Friend request sent")
												},
												function(errResponse) {
													console
															.error('Error while sending Friend requests');

												});
							}
							;

							/*
							 * GET MY FRIENDS
							 * LIST.....................................
							 */
							self.getMyFriends = function() {
								console.log("getting My Friends")
								FriendService
										.getMyFriends()
										.then(
												function(d) {
													self.friends = d;
													console
															.log("Got the friends list")
												},
												function(errResponse) {
													console
															.error('Error while fetching Friend    ...controller.js');

												});
							};

							self.updateFriendRequest = function(friend, id) {
								FriendService
										.updateFriendRequest(friend, id)
										.then(
												self.fetchAllFriends,
												function(errResponse) {
													console
															.error('Error while updating Friend in friendcontroller.js');
												});
							};

							self.deleteFriend = function(id) {
								FriendService
										.deleteFriend(id)
										.then(
												self.fetchAllFriends,
												function(errResponse) {
													console
															.error('Error while deleting Friend in friend controller.js');
												});
							};

							self.fetchAllUsers = function() {
								FriendService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
													console
															.log("get all the users in friendcontroller.js....")
												},
												function(errResponse) {
													console
															.error('Error while fetching Friendcontroller.js');
												});
							};
							self.fetchAllUsers();
							self.getMyFriends();

						} ]);
