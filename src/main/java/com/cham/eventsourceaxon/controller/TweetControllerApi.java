package com.cham.eventsourceaxon.controller;

import com.cham.eventsourceaxon.command.CreateTweetCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/tweets")
@RestController
public class TweetControllerApi {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    public TweetControllerApi(CommandGateway commandGateway, EventStore eventStore){
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> createTweet(@RequestBody TweetOwner user) {
        System.out.println("Inside TweetControllerApi.createTweet()..");
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateTweetCommand(id, user.name, user.txt));
    }

    static class TweetOwner {
        public String name;
        public String txt;
    }

    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }
}
