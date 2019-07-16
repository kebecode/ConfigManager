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

package com.akardoo.configmanager.api;

import java.util.Collection;
import java.util.List;

/**
 * @author Kevin (CookLoco)
 */
public interface IConfig {

    <T> T get(String path, T def);

    boolean contains(String path);

    Object get(String path);

    Object getDefault(String path);

    void set(String path, Object value);

    //getSection?

    Collection<String> getKeys();

    Collection<String> getKeys(boolean deep);

    byte getByte(String path);

    byte getByte(String path, byte def);

    List<Byte> getByteList(String path);

    short getShort(String path);

    short getSHort(String path, short def);

    List<Short> getShortList(String path);

    int getInt(String path);

    int getInt(String path, int def);

    List<Integer> getIntList(String path);

    long getLong(String path);

    long getLong(String path, long def);

    List<Long> getLongList(String path);

    float getFloat(String path);

    float getFloat(String path, float def);

    List<Float> getFloatList(String path);

    double getDouble(String path);

    double getDouble(String path, double def);

    List<Double> getDoubleList(String path);

    boolean getBoolean(String path);

    boolean getBoolean(String path, boolean def);

    List<Boolean> getBooleanList(String path);

    char getChar(String path);

    char getChar(String path, char def);

    List<Character> getCharList(String path);

    String getString(String path);

    String getString(String path, String def);

    List<String> getStringList(String path);


    /*------------------------------------------------------------------------*/
    List<?> getList(String path);

    List<?> getList(String path, List<?> def);

}
