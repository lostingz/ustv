/**
 * birkhoff
 * Created on 2017年02月11日
 */
package service;

import java.util.List;

import model.SearchTypeEnum;
import model.TV;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public interface TVService {
    /**
     * 根据名称和类型搜索资源
     * @param name 名称，支持中英文
     * @param type 类型：TV美剧 SEED种子 MOVIE 电影
     * @return TVList
     */
    List<TV> getTVList(String name, SearchTypeEnum type);
}
