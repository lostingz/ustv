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
    private String quality;
    private String captions;
    private List<DownloadLink> linkList;
    private String summary;

    public Episode(String name, List<DownloadLink> linkList) {
        this.name = name;
        this.linkList = linkList;
    }

    public Episode(String name, String size, String quality, String captions, List<DownloadLink> linkList) {
        this.name = name;
        this.size = size;
        this.quality = quality;
        this.captions = captions;
        this.linkList = linkList;
    }

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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCaptions() {
        return captions;
    }

    public void setCaptions(String captions) {
        this.captions = captions;
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
