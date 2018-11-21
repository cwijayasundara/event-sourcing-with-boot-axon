package com.cham.eventsourceaxon.command;

import com.cham.eventsourceaxon.base.BaseCommand;

public class CreateTweetCommand extends BaseCommand<String> {

    public final String tweetCreator;
    public final String tweetTxt;

    public CreateTweetCommand(String id, String tweetCreator, String tweetTxt){
        super(id);
        System.out.println("Inside CreateTweetCommand.CreateTweetCommand()..");
        this.tweetCreator = tweetCreator;
        this.tweetTxt = tweetTxt;
    }

}
