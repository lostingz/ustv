/**
 * birkhoff
 * Created on 2017年02月11日
 */
package util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class SearchUtil {
    public static String getSearchUrl(String name,int type){
        if(StringUtils.isNoneBlank(name)){
            name=StringUtils.replace(name," ","+");
            return Const.SEARCH_URL+String.format("?keyword=%s&range=%s",name,type);
        }
        return null;
    }
}
