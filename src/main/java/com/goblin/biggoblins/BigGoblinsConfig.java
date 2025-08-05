package com.goblin.biggoblins;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

/**
 * Configuration options for the Big Goblins plugin.
 */
@ConfigGroup("biggoblins")
public interface BigGoblinsConfig extends Config
{
    /**
     * Name of the NPC to track.
     *
     * @return NPC name
     */
    @ConfigItem(
        keyName = "npcName",
        name = "NPC Name",
        description = "Name of the NPC to track kills for",
        position = 0
    )
    default String npcName()
    {
        return "Goblin";
    }

    /**
     * Whether NPC highlighting is enabled.
     *
     * @return true if highlights should be shown
     */
    @ConfigItem(
        keyName = "highlightNpcs",
        name = "Highlight NPCs",
        description = "Highlight the configured NPCs in game",
        position = 1
    )
    default boolean highlightNpcs()
    {
        return true;
    }

    /**
     * Whether the side panel is visible.
     *
     * @return true if the side panel should be shown
     */
    @ConfigItem(
        keyName = "showPanel",
        name = "Show Side Panel",
        description = "Enable the Big Goblins side panel",
        position = 2
    )
    default boolean showPanel()
    {
        return true;
    }
}
