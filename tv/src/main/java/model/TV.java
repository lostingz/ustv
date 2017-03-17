/**
 * birkhoff
 * Created on 2017年02月11日
 */
package model;

import util.Const;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class TV {
    private String name;
    private String detailUrl;
    private String updateDate;
    private String status;
    private String backDate;
    private String imgUrl;

    public TV() {
    }

    public TV(String name, String detailUrl, String updateDate, String status, String backDate) {
        this.name = name;
        this.detailUrl = detailUrl;
        this.updateDate = updateDate;
        this.status = status;
        this.backDate = backDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s",name, Const.SITE+detailUrl,status,updateDate,backDate);
    }
}
