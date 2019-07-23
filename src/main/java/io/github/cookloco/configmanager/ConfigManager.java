/*
 * MIT License
 *
 * Copyright (c) 2019. Kevin (CookLoco)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.cookloco.configmanager;

import io.github.cookloco.configmanager.api.CustomConfig;
import io.github.cookloco.configmanager.spigot.ConfigBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author Kevin (CookLoco)
 */
public class ConfigManager extends JavaPlugin implements Listener {

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        File file = new File(this.getDataFolder(), "config.yml");
        CustomConfig config = new ConfigBukkit(file);


        config.addDefault("test.1", 10, "This is a test comment");
        config.addDefault("test.test", 12, "This is the second test");
        config.addDefault("prueba.hola", true, "This a prueba", "hola");
        config.addDefault("prueba.adios", "adios");
        config.putComments("prueba", "Comentario en prueba");
        config.putComments("test", "Comentario en test");

        config.setNewLinePerKey(true);

        config.save();

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Hola");
    }
}
