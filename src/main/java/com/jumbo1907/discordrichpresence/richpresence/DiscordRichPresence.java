package com.jumbo1907.discordrichpresence.richpresence;


import com.jumbo1907.discordrichpresence.richpresence.listeners.ReadyListener;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;

public class DiscordRichPresence {

    private String clientID;

    public DiscordRichPresence(String clientID) {
        this.clientID = clientID;
    }


    public void login() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyListener()).build();
        DiscordRPC.discordInitialize(clientID, handlers, true);

    }
}
