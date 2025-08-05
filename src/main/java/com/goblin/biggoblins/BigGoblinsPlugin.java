package com.goblin.biggoblins;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.NpcLootReceived;
import net.runelite.api.events.GameTick;
import net.runelite.api.GameState;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.time.Instant;

/**
 * Main plugin class for Big Goblins. Tracks kills of a configured NPC,
 * highlights them in the world and exposes a side panel with a running
 * kill count. The plugin avoids any automation and only provides
 * quality‑of‑life features consistent with RuneLite's plugin hub rules.
 */
@Slf4j
@PluginDescriptor(
    name = "Big Goblins",
    description = "Quality‑of‑life tools for goblin hunting and clan activities",
    tags = {"goblin", "clan", "qol"}
)
public class BigGoblinsPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private BigGoblinsConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private BigGoblinsPanel panel;

    // Overlay instance for highlighting NPCs
    private Overlay overlay;

    // Nav button for the side panel
    private NavigationButton navButton;

    // Kill count of configured NPCs this session
    private int killCount;

    // Timestamp of plugin startup for session length display
    private Instant sessionStart;

    @Provides
    BigGoblinsConfig provideConfig(final ConfigManager configManager)
    {
        return configManager.getConfig(BigGoblinsConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        sessionStart = Instant.now();
        killCount = 0;

        // Create overlay and add to manager
        overlay = new BigGoblinsOverlay(client, config);
        overlayManager.add(overlay);

        // Load icon from resources
        final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");

        // Create a navigation button to open the plugin panel in the side bar
        navButton = NavigationButton.builder()
            .tooltip("Big Goblins")
            .icon(icon)
            .priority(5)
            .panel(panel)
            .build();
        if (config.showPanel())
        {
            clientToolbar.addNavigation(navButton);
        }

        log.info("Big Goblins plugin started");
    }

    @Override
    protected void shutDown() throws Exception
    {
        // Remove overlay and panel
        overlayManager.remove(overlay);
        if (navButton != null)
        {
            clientToolbar.removeNavigation(navButton);
        }
        killCount = 0;
        panel.updateKillCount(0);
        log.info("Big Goblins plugin stopped");
    }

    @Subscribe
    public void onNpcLootReceived(final NpcLootReceived event)
    {
        // Called when the player receives loot from an NPC. We use this as a
        // reliable indicator that the NPC has died at the player's hand.
        final NPC npc = event.getNpc();
        final String search = config.npcName().toLowerCase();
        final String name = npc.getName();
        if (name != null && name.toLowerCase().contains(search))
        {
            killCount++;
            panel.updateKillCount(killCount);
        }
    }

    @Subscribe
    public void onGameStateChanged(final GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOGGED_IN)
        {
            // Greet the player on login
            log.debug("Player logged in. Big Goblins ready.");
        }
    }

    @Subscribe
    public void onGameTick(final GameTick tick)
    {
        // Could be used for additional real‑time updates, such as session timers.
    }
}
