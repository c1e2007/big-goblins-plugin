package com.goblin.biggoblins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

/**
 * Overlay to highlight configured NPCs in the game world. This draws the
 * NPC's convex hull in translucent green to make it easier to spot. The
 * highlight can be toggled via the plugin configuration.
 */
public class BigGoblinsOverlay extends Overlay
{
    private final Client client;
    private final BigGoblinsConfig config;

    @Inject
    public BigGoblinsOverlay(final Client client, final BigGoblinsConfig config)
    {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(final Graphics2D graphics)
    {
        if (!config.highlightNpcs())
        {
            return null;
        }

        final String search = config.npcName().toLowerCase();
        for (NPC npc : client.getNpcs())
        {
            final String name = npc.getName();
            if (name == null)
            {
                continue;
            }
            if (!name.toLowerCase().contains(search))
            {
                continue;
            }
            final Shape hull = npc.getConvexHull();
            if (hull == null)
            {
                continue;
            }
            // Draw outline
            graphics.setColor(new Color(0, 255, 0, 180));
            graphics.draw(hull);
            // Fill with translucent color
            graphics.setColor(new Color(0, 255, 0, 40));
            graphics.fill(hull);
        }
        return null;
    }
}
