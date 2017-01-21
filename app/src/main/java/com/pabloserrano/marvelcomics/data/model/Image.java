
package com.pabloserrano.marvelcomics.data.model;

import java.util.HashMap;
import java.util.Map;

public class Image {

    private String path;
    private String extension;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
