package model; /**
 * birkhoff
 * Created on 2017年02月11日
 */

import java.util.List;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class Season {
    /**
     * 剧集季名
     */
    private String name;
    /**
     * 总集数
     */
    private String sum;
    /**
     * 剧集
     */
    private List<Episode> episodeList;
    /**
     * 海报图片url
     */
    private String posterUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
