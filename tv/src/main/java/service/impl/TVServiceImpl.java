/**
 * birkhoff
 * Created on 2017年02月11日
 */
package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.SearchTypeEnum;
import model.TV;
import service.TVService;
import util.HtmlUtil;
import util.SearchUtil;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class TVServiceImpl implements TVService{
    @Override
    public List<TV> getTVList(String name, SearchTypeEnum type) {
        String searchUrl=SearchUtil.getSearchUrl(name,type.ordinal());
        List<TV> result=new ArrayList<TV>();
        if(StringUtils.isNoneBlank(searchUrl)){
            try {
                Document doc= HtmlUtil.getDocument(searchUrl);
                Elements trs=doc.select(".seedtable tbody tr");
                int size=trs.size();
                if(size>2){
                    for (int i=2;i<size-1;i++){
                        Element tr=trs.get(i);
                        Elements tds=tr.select("td");
                        String tvName=tds.get(1).text();
                        String url=tds.select("a").first().attr("href");
                        String status=tds.get(2).text();
                        String updateDate=tds.get(3).text();
                        String backDate=tds.get(4).text();
                        result.add(new TV(tvName,url,updateDate,status,backDate));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TVServiceImpl tvService=new TVServiceImpl();
        List<TV> list=tvService.getTVList("walking dead",SearchTypeEnum.TV);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
