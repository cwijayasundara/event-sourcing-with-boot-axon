package com.cham.eventsourceaxon.event;

import com.cham.eventsourceaxon.base.BaseEvent;

public class TweetCreatedEvent extends BaseEvent<String> {

    public final String tweetCreator;
    public final String tweetText;

    public TweetCreatedEvent(String id, String tweetCreator, String tweetText) {
        super(id);
        this.tweetCreator = tweetCreator;
        this.tweetText = tweetText;
    }
}
