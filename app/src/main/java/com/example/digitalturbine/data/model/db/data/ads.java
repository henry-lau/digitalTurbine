package com.example.digitalturbine.data.model.db.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ads", strict = false)
public class ads {

    public ads() {
    }

    public ads(List<ad> items) {
        this.adList = items;
    }

    @ElementList(entry = "ad", inline = true)
    public List<ad> adList;
}
