/**
 * birkhoff
 * Created on 2017年02月11日
 */
package model;

import java.util.List;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class Episode {
    private String name;
    private String size;
    private String type;
    private List<DownloadLink> linkList;
    private String summary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DownloadLink> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<DownloadLink> linkList) {
        this.linkList = linkList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
