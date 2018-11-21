package com.cham.eventsourceaxon.domain;

import com.cham.eventsourceaxon.command.CreateTweetCommand;
import com.cham.eventsourceaxon.event.TweetCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@Aggregate
public class Tweet implements Serializable {
    private static final long serialVersionId = 1L;

    @AggregateIdentifier
    private String id;

    private String user;
    private String tweetTxt;

    @CommandHandler
    public Tweet(CreateTweetCommand command){
        String id = command.id;
        String name =  command.tweetCreator;
        String txt = command.tweetTxt;
        Assert.hasLength(id, "missing id");
        Assert.hasLength(name, "missing tweet user");
        AggregateLifecycle.apply(new TweetCreatedEvent(id, name, txt));
    }

    public Tweet(){}

    @EventSourcingHandler
    protected void on(TweetCreatedEvent event){
        this.id = event.id;
        this.user = event.tweetCreator;
        this.tweetTxt = event.tweetText;
    }

}
