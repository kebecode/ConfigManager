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

package com.akardoo.configmanager.bungeecord;

import com.akardoo.configmanager.api.CustomConfig;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Kevin (CookLoco)
 */
public class ConfigBungee extends CustomConfig {

    Configuration config;

    public ConfigBungee(File file) {
        super(file);
    }

    public Configuration getConfig() {
        return config;
    }

    @Override
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.save();
    }

    @Override
    public void load() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.load();
    }

    @Override
    public Object get(String path) {
        return config.get(path);
    }

    @Override
    public <T> Object get(String path, T def) {
        return config.get(path, def);
    }

    @Override
    public boolean contains(String path) {
        return config.contains(path);
    }

    @Override
    public void addDefault(String path, Object defaultValue) {
        if (!contains(path)) {
            config.set(path, defaultValue);
        }
    }

    @Override
    public void addDefault(String path, Object defaultValue, String... comments) {
        if (!contains(path)) {
            super.addDefault(path, defaultValue, comments);
            config.set(path, defaultValue);
        }
    }

    @Override
    public Object getDefault(String path) {
        return config.getDefault(path);
    }

    @Override
    public Collection<String> getKeys() {
        return config.getKeys();
    }

    @Override
    public Collection<String> getKeys(boolean deep) {
        return config.getKeys();
    }

    @Override
    public byte getByte(String path) {
        return config.getByte(path);
    }

    @Override
    public byte getByte(String path, byte def) {
        return config.getByte(path, def);
    }

    @Override
    public List<Byte> getByteList(String path) {
        return config.getByteList(path);
    }

    @Override
    public short getShort(String path) {
        return config.getShort(path);
    }

    @Override
    public short getShort(String path, short def) {
        return config.getShort(path, def);
    }

    @Override
    public List<Short> getShortList(String path) {
        return config.getShortList(path);
    }

    @Override
    public int getInt(String path) {
        return config.getInt(path);
    }

    @Override
    public int getInt(String path, int def) {
        return config.getInt(path, def);
    }

    @Override
    public List<Integer> getIntList(String path) {
        return config.getIntList(path);
    }

    @Override
    public long getLong(String path) {
        return config.getLong(path);
    }

    @Override
    public long getLong(String path, long def) {
        return config.getLong(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return config.getLongList(path);
    }

    @Override
    public float getFloat(String path) {
        return config.getFloat(path);
    }

    @Override
    public float getFloat(String path, float def) {
        return config.getFloat(path, def);
    }

    @Override
    public List<Float> getFloatList(String path) {
        return config.getFloatList(path);
    }

    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    @Override
    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    @Override
    public List<Double> getDoubleList(String path) {
        return config.getDoubleList(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        return config.getBoolean(path, def);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return config.getBooleanList(path);
    }

    @Override
    public char getChar(String path) {
        return config.getChar(path);
    }

    @Override
    public char getChar(String path, char def) {
        return config.getChar(path, def);
    }

    @Override
    public List<Character> getCharList(String path) {
        return config.getCharList(path);
    }

    @Override
    public String getString(String path) {
        return config.getString(path);
    }

    @Override
    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    @Override
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    @Override
    public List<?> getList(String path) {
        return config.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return config.getList(path, def);
    }
}
