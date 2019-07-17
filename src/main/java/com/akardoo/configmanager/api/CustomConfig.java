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

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * @author Kevin (CookLoco)
 */
@Getter
public abstract class CustomConfig implements IConfig {

    protected File file;
    protected Map<String, List<String>> comments = new LinkedHashMap<>();
    @Setter
    protected boolean newLinePerKey = false;

    protected CustomConfig(@NotNull File file) {
        Validate.notNull(file, "Configuration file can not be null");
        this.file = file;
        load();
    }

    public void reload() {
        load();
    }

    public void save() {
        List<String> configContent = getConfigContent();

        try (BufferedWriter configWriter = new BufferedWriter(new FileWriter(file))) {
            configWriter.write("");
            for (int lineIndex = 0; lineIndex < configContent.size(); lineIndex++) {
                String configLine = configContent.get(lineIndex);

                String configKey = null;
                if (!configLine.startsWith("#") && configLine.contains(":"))
                    configKey = getPathToKey(configContent, lineIndex, configLine);
                if (configKey != null && this.comments.containsKey(configKey)) {
                    int numOfSpaces = getPrefixSpaceCount(configLine);
                    StringBuilder spacePrefix = new StringBuilder();
                    for (int i = 0; i < numOfSpaces; i++) {
                        spacePrefix.append(" ");
                    }
                    List<String> configComments = this.comments.get(configKey);

                    if (configComments != null) {
                        for (String comment : configComments) {
                            configWriter.append(spacePrefix.toString()).append("# ").append(comment);
                            configWriter.newLine();
                        }
                    }
                }

                boolean isComment = configLine.startsWith("#");

                if (configLine.startsWith("-") || configLine.startsWith("  -") || configLine.startsWith("    -") || configLine.startsWith("      -")) {
                    configWriter.append("  ").append(configLine);
                } else {
                    configWriter.append(configLine);
                }
                configWriter.newLine();

                if (newLinePerKey && lineIndex < configContent.size() - 1 && !isComment) {
                    String nextConfigLine = configContent.get(lineIndex + 1);
                    if (nextConfigLine != null && !nextConfigLine.startsWith(" ")) {
                        if (!nextConfigLine.startsWith("'") && !nextConfigLine.startsWith("-")) configWriter.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void load() {
        List<String> configLines = getConfigContent();

        if (configLines.isEmpty()) {
            System.out.println(file.getName() + " doesn't have nothing to load");
            return;
        }

        boolean hasHeader = !trim(configLines.get(0)).isEmpty();

        Map<String, List<String>> configComments = new LinkedHashMap<>();
        for (int lineIndex = 0; lineIndex < configLines.size(); lineIndex++) {
            String configLine = configLines.get(lineIndex);
            String trimmedLine = trimPrefixSpaces(configLine);
            if (trimmedLine.startsWith("#") && (lineIndex > 0 || !hasHeader)) {
                String configKey = getPathToComment(configLines, lineIndex, configLine);
                if (configKey != null) {
                    List<String> keyComments = configComments.get(configKey);
                    if (keyComments == null) keyComments = new ArrayList<>();
                    keyComments.add(trimmedLine.substring(trimmedLine.startsWith("# ") ? 2 : 1));
                    configComments.put(configKey, keyComments);
                }
            }
        }
    }

    @Override
    public void addDefault(String path, Object defaultValue, String... comments) {
        if (defaultValue != null && comments != null && comments.length > 0 && !this.comments.containsKey(path)) {
            putComments(path, comments);
        }
    }

    @Override
    public void set(String key, Object value) {
        if (value != null) {
            if (getComments(key).size() > 0){
                this.comments.put(key, getComments(key));
            } else {
                this.comments.remove(key);
            }
        } else {
            this.comments.remove(key);
        }
    }

    @Override
    public void set(String key, Object value, String... comments) {
        if (value != null) {
            if (comments != null) {
                if (comments.length > 0) {
                    putComments(key, comments);
                } else {
                    this.comments.remove(key);
                }
            }
        } else {
            this.comments.remove(key);
        }
    }

    @Override
    public List<String> getComments(String path) {
        return comments.containsKey(path) ? new ArrayList<>(comments.get(path)) : new ArrayList<>();
    }

    private void putComments(String path, String... comments) {
        List<String> commentsList = new ArrayList<>();
        for (String comment : comments) {
            if (comment != null) {
                commentsList.add(comment);
            } else {
                commentsList.add("");
            }
        }
        this.comments.put(path, commentsList);
    }

    private List<String> getConfigContent() {
        List<String> configContent = new ArrayList<>();
        try (BufferedReader configReader = new BufferedReader(new FileReader(file))){
            String configReadLine;
            while ((configReadLine = configReader.readLine()) != null) configContent.add(configReadLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configContent;
    }

    private String getPathToComment(List<String> configContents, int lineIndex, String configLine) {
        if (configContents != null && lineIndex >= 0 && lineIndex < configContents.size() - 1 && configLine != null) {
            String trimmedConfigLine = trimPrefixSpaces(configLine);
            if (trimmedConfigLine.startsWith("#")) {
                int currentIndex = lineIndex;
                while (currentIndex < configContents.size() - 1) {
                    currentIndex++;
                    String currentLine = configContents.get(currentIndex);
                    String trimmedCurrentLine = trimPrefixSpaces(currentLine);
                    if (!trimmedCurrentLine.startsWith("#")) {
                        if (trimmedCurrentLine.contains(":")) {
                            return getPathToKey(configContents, currentIndex, currentLine);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return null;
    }

    private String getPathToKey(List<String> configContents, int lineIndex, String configLine) {
        if (configContents != null && lineIndex >= 0 && lineIndex < configContents.size() && configLine != null) {
            if (!configLine.startsWith("#") && configLine.contains(":")) {
                int spacesBeforeKey = getPrefixSpaceCount(configLine);
                String key = trimPrefixSpaces(configLine.substring(0, configLine.indexOf(':')));
                if (spacesBeforeKey > 0) {
                    int currentIndex = lineIndex;
                    int previousSpacesBeforeCurrentLine = -1;
                    boolean atZeroSpaces = false;

                    while (currentIndex > 0) {
                        currentIndex--;
                        String currentLine = configContents.get(currentIndex);
                        int spacesBeforeCurrentLine = getPrefixSpaceCount(currentLine);
                        if (trim(currentLine).isEmpty())
                            break;
                        if (!trim(currentLine).startsWith("#")) {
                            if (spacesBeforeCurrentLine < spacesBeforeKey) {
                                if (currentLine.contains(":")) {
                                    if (spacesBeforeCurrentLine > 0 || !atZeroSpaces) {
                                        if (spacesBeforeCurrentLine == 0)
                                            atZeroSpaces = true;
                                        if (previousSpacesBeforeCurrentLine == -1
                                                || spacesBeforeCurrentLine < previousSpacesBeforeCurrentLine) {
                                            previousSpacesBeforeCurrentLine = spacesBeforeCurrentLine;
                                            key = trimPrefixSpaces(currentLine.substring(0, currentLine.indexOf(":")))
                                                    + "." + key;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                return key;
            }
        }
        return null;
    }

    private int getPrefixSpaceCount(String aString) {
        int spaceCount = 0;
        if (aString != null && aString.contains(" ")) {
            for (char aCharacter : aString.toCharArray()) {
                if (aCharacter == ' ')
                    spaceCount++;
                else
                    break;
            }
        }
        return spaceCount;
    }

    private String trim(String aString) {
        return aString != null ? aString.trim().replace(System.lineSeparator(), "") : "";
    }

    private String trimPrefixSpaces(String aString) {
        if (aString != null) {
            while (aString.startsWith(" "))
                aString = aString.substring(1);
        }
        return aString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomConfig that = (CustomConfig) o;

        return Objects.equals(file, that.file);

    }

    @Override
    public int hashCode() {
        return file != null ? file.hashCode() : 0;
    }
}
