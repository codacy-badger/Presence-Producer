package com.jumbo1907.discordrichpresence.richpresence.listeners;

import com.jumbo1907.discordrichpresence.utils.Logger;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class ReadyListener implements ReadyCallback {

    @Override
    public void apply(DiscordUser discordUser) {
        Logger.SUCCESS.out("Welcome " + discordUser.username + "#" + discordUser.discriminator + "!");
    }

}
