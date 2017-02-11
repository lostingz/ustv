package model; /**
 * birkhoff
 * Created on 2017年02月11日
 */

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
}
