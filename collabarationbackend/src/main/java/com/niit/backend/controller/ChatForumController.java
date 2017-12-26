package com.niit.backend.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.backend.model.Message;
import com.niit.backend.model.OutputMessage;

@Controller
public class ChatForumController {
	
	@MessageMapping("/chat_forum")        //send message
	@SendTo("/topic/message")       //Receive messagess
	public OutputMessage sendMessage(Message message){
		System.out.println("Calling the method sendMessage");
		System.out.println("Message: "+message.getMessage());
		
		
		return new OutputMessage(message,new Date());  //appending current date
		
}

}
