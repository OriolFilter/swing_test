package com.codebind;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;

public class jdktest {
    public static void main(String[] args) {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "w-Klr7bsVCcA5vArATxcdMIf7To1Plqq";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
//        handlers.ready = () -> System.out.println("Ready");
        handlers.ready = new DiscordEventHandlers.OnReady() {
            @Override
            public void accept(DiscordUser discordUser) {
                System.out.println("Ready");
            }
        };
        System.out.println("Starte");
        lib.Discord_Initialize(applicationId,handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = "Details: ";
        presence.state = "Status: ";
        lib.Discord_UpdatePresence(presence);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }
}
