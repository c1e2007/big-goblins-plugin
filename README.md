# Big Goblins

Big Goblins is a quality ‑‭of‭‑‭life plugin for the Old School RuneScape RuneLite client.
It adds a side panel which tracks how many of a chosen NPC you have killed during
your current session and optionally highlights those NPCs in‭game. The plugin was
designed as a gentle introduction to external plugin development and complies with
Jagex’s third‭party client rules—there is **no automation** or botting logic.

## Features

- **Side panel kill counter** – shows a running total of how many of the configured NPC (by default
  Goblins) you have killed. A reset button allows you to start a new count at any
  time.
- **NPC highlighting** – highlights the configured NPCs on the game world with a green
  outline to make them easier to see. This is optional and can be toggled off in
  the plugin settings.
- **Configurable NPC name** – track any NPC by changing the **NPC Name** in the
  plugin’s configuration panel (for example, to count Cow or Imp kills instead).

## Getting Started

This project is based on the official RuneLite external plugin template. To
develop locally, clone this repository and open it with IntelliJ IDEA. Ensure you have Java 11
installed. The provided test class `BigGoblinsPluginTest` will launch RuneLite
with the plugin loaded for development purposes.

To build a distributable JAR run:

```
./gradlew build shadowJar
```

The resulting file will be located in `build/libs/big-goblins-1.0-SNAPSHOT-all.jar`.
This jar can be installed into RuneLite via the *External Plugins* configuration.

## Adding to the Plugin Hub

To publish Big Goblins on the official RuneLite Plugin Hub:

1. Fork the `runelite/plugin-hub` repository and create a branch for your plugin.
2. Create a file named `big-goblins` in the `plugin-hub/plugins` directory with two fields:

   ````
   repository=https://github.com/yourusername/big-goblins
   commit=<latest commit hash>
   ````

3. Submit a pull request from your fork. RuneLite maintainers will review the plugin for adherence to the rules and, once approved, it will appear in the Plugin Hub.

## License

Distributed under the BSD 2‭Clause “Simplified” License. See the `LICENSE` file for details.
