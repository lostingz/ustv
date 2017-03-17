/**
 * birkhoff
 * Created on 2017年02月11日
 */
package service;

import java.io.IOException;
import java.util.List;

import model.SearchTypeEnum;
import model.Season;
import model.TV;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public interface TVService {
    /**
     * 根据名称和类型搜索资源
     * @param name 名称，支持中英文
     * @param type 类型：TV美剧 SEED种子
     * @return TVList
     */
    List<TV> getTVList(String name, SearchTypeEnum type) throws IOException;

    /**
     * 获取剧集
     * @param url
     * @param seasonNum 季数字,第一季填1
     * @return Season
     * @throws IOException
     */
    Season getSeason(String url, int seasonNum) throws IOException;

    /**
     * 获取排行榜
     * @param num 个数
     * @return
     * @throws IOException
     */
    List<TV> getRankTopList(int num) throws IOException;
}
