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

import com.google.common.collect.Lists;

import model.DownloadLink;
import model.Episode;
import model.SearchTypeEnum;
import model.Season;
import model.TV;
import service.TVService;
import util.Const;
import util.HtmlUtil;
import util.SearchUtil;

/**
 * @author birkhoff<a href="mailto:zgmqxj012@163.com">birkhoff</a>
 * @version $Id$
 */
public class TVServiceImpl implements TVService {
    @Override
    public List<TV> getTVList(String name, SearchTypeEnum type) throws IOException {
        String searchUrl = SearchUtil.getSearchUrl(name, type.ordinal());
        List<TV> result = new ArrayList<TV>();
        if (StringUtils.isNoneBlank(searchUrl)) {
            Document doc = HtmlUtil.getDocument(searchUrl);
            Elements trs = doc.select(".latesttable tbody tr");
            int size = trs.size();
            result=getTvByTableHtml(trs, size);
        }
        return result;
    }

    public Season getSeason(String url, int seasonNum) throws IOException {
        Season season = new Season();
        Document doc = HtmlUtil.getDocument(url);
        Element picElem=doc.select("#spic").first();
        Elements seedList = doc.select("#seedlist tr");
        List<Episode> episodeList = new ArrayList<Episode>();
        for (Element seed : seedList) {
            Elements tds = seed.select("td");
            String name = tds.get(1).text();
            Element urlTag = tds.get(2).select("a").first();
            String downloadUrl="";
            String type="";
            if(urlTag!=null){
                downloadUrl = urlTag.attr("href");
                urlTag.attr("title");
            }
            String size=tds.get(3).text();
            String qulity=tds.get(4).text();
            String captions=tds.get(5).text();
            List<DownloadLink> dowloadUrlList = Lists.newArrayList();
            dowloadUrlList.add(new DownloadLink(type, downloadUrl));
            Episode episode = new Episode(name, size,qulity,captions,dowloadUrlList);
            episodeList.add(episode);
        }
        season.setName("" + seasonNum);
        season.setPosterUrl(picElem.attr("src"));
        season.setEpisodeList(episodeList);
        return season;
    }

    @Override
    public List<TV> getRankTopList(int startPageNum) throws IOException {
        String base=Const.SITE+"/summary.html/index/p/"+startPageNum+".html";
        Document doc=HtmlUtil.getDocument(base);
        Elements trs = doc.select(".latesttable tbody tr");
        int size = trs.size();
        return getTvByTableHtml(trs, size);
    }

    private List<TV> getTvByTableHtml(Elements trs, int size) {
        List<TV> result = new ArrayList<TV>();
        if (size > 1) {
            for (int i = 1; i < size - 1; i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("td");
                String tvName = tds.get(1).text();
                String url = Const.SITE + tds.select("a").first().attr("href");
                String status = tds.get(2).text();
                String updateDate = tds.get(3).text();
                String backDate = tds.get(4).text();
                result.add(new TV(tvName, url, updateDate, status, backDate));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TVServiceImpl tvService = new TVServiceImpl();
        try {
            List<TV> list = tvService.getTVList("the walking dead", SearchTypeEnum.TV);
            for (int i = 0; i < list.size(); i++) {
                TV tv = list.get(i);
                Season season = tvService.getSeason(tv.getDetailUrl(), 1);
            }
            List<TV> result=tvService.getRankTopList(1);
            System.out.println(result.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
