
package com.pabloserrano.marvelcomics.data.model;

import java.util.HashMap;
import java.util.Map;

public class Item__ {

    private String resourceURI;
    private String name;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
