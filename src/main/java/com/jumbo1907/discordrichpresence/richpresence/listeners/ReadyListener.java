package com.jumbo1907.discordrichpresence.richpresence.listeners;

import com.jumbo1907.discordrichpresence.Main;
import com.jumbo1907.discordrichpresence.utils.Logger;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

import java.io.IOException;

public class ReadyListener implements ReadyCallback {

    private boolean userFound = false;

    public ReadyListener() {
        Logger.SUCCESS.out("Successfully started listening for callback.");
        new Thread(() -> {
            while (!userFound) {
                DiscordRPC.discordRunCallbacks();
            }
        }).start();

    }

    @Override
    public void apply(DiscordUser discordUser) {
        Logger.SUCCESS.out("Welcome " + discordUser.username + "#" + discordUser.discriminator + "!");
        userFound = true;

               try {
                    Main.mainApplication.addUserInformation(discordUser.userId,discordUser.avatar, discordUser.username+"#"+ discordUser.discriminator);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

}
