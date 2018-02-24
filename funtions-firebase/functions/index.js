'use-strict'

const functions = require('firebase-functions');
const admin=require('firebase-admin');
admin.initializeApp(functions.config().firebase);

 exports.sendNotification = functions.database.ref('/events').onWrite(event =>{

	var message ={
			
			notification :{
				title:"New Friend request",
				body:  "sent you friend request",
				icon:"default",
				click_action: "com.example.hardik.myapplication"
			}
		};
	
	return admin.messaging().send(message).then(response =>{
		console.log('this is notification feature');
	});	
 
 });