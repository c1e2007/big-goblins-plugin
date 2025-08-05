package com.goblin.biggoblins;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

/**
 * Stand‑alone entry point to launch RuneLite with the Big Goblins plugin
 * pre‑loaded. This is used only during development for testing; it
 * shouldn't be included in the plugin jar distributed via the plugin hub.
 */
public class BigGoblinsPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(BigGoblinsPlugin.class);
        RuneLite.main(args);
    }
}
