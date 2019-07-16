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

package com.akardoo.configmanager.spigot;

import com.akardoo.configmanager.api.CustomConfig;
import lombok.Getter;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author Kevin (CookLoco)
 */
@Getter
public class ConfigBukkit extends CustomConfig {

    private FileConfiguration config;

    protected ConfigBukkit(File file) {
        super(file);
    }

    public void save() {

    }

    protected void save(File file) {

    }

    protected void load(File file) {
        config = YamlConfiguration.loadConfiguration(file);
        config.options().header("");
    }

    public void reload() {

    }

    public <T> T get(String path, T def) {
        return null;
    }

    public boolean contains(String path) {
        return false;
    }

    public Object get(String path) {
        return null;
    }

    public Object getDefault(String path) {
        return null;
    }

    public void set(String path, Object value) {

    }

    public Collection<String> getKeys() {
        return null;
    }

    public Collection<String> getKeys(boolean deep) {
        return null;
    }

    public byte getByte(String path) {
        return 0;
    }

    public byte getByte(String path, byte def) {
        return 0;
    }

    public List<Byte> getByteList(String path) {
        return null;
    }

    public short getShort(String path) {
        return 0;
    }

    public short getSHort(String path, short def) {
        return 0;
    }

    public List<Short> getShortList(String path) {
        return null;
    }

    public int getInt(String path) {
        return 0;
    }

    public int getInt(String path, int def) {
        return 0;
    }

    public List<Integer> getIntList(String path) {
        return null;
    }

    public long getLong(String path) {
        return 0;
    }

    public long getLong(String path, long def) {
        return 0;
    }

    public List<Long> getLongList(String path) {
        return null;
    }

    public float getFloat(String path) {
        return 0;
    }

    public float getFloat(String path, float def) {
        return 0;
    }

    public List<Float> getFloatList(String path) {
        return null;
    }

    public double getDouble(String path) {
        return 0;
    }

    public double getDouble(String path, double def) {
        return 0;
    }

    public List<Double> getDoubleList(String path) {
        return null;
    }

    public boolean getBoolean(String path) {
        return false;
    }

    public boolean getBoolean(String path, boolean def) {
        return false;
    }

    public List<Boolean> getBooleanList(String path) {
        return null;
    }

    public char getChar(String path) {
        return 0;
    }

    public char getChar(String path, char def) {
        return 0;
    }

    public List<Character> getCharList(String path) {
        return null;
    }

    public String getString(String path) {
        return null;
    }

    public String getString(String path, String def) {
        return null;
    }

    public List<String> getStringList(String path) {
        return null;
    }

    public List<?> getList(String path) {
        return null;
    }

    public List<?> getList(String path, List<?> def) {
        return null;
    }
}
