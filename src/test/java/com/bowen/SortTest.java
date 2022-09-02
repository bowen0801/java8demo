package com.bowen;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * description
 *
 * @version v1.0.0
 * @since 2019年10月10日
 */
public class SortTest {

    // 集合sort多字段排序
    public static void main(String[] args) {
        List<Banner> list = new ArrayList<Banner>();
        list.add(new Banner(3L,1));
        list.add(new Banner(1L,1));
        list.add(new Banner(2L,2));
        list.add(new Banner(4L,3));

        list.sort(new Comparator<Banner>() {
            @Override
            public int compare(Banner o1, Banner o2) {
                /*if (o1.getOrders() != o2.getOrders()){*/
                    return o1.getOrders() - o2.getOrders();
                /*}*/
                //return o1.getId().intValue() - o2.getId().intValue();
            }
        });
        for(Banner banner : list) {
            System.out.println(banner.getId());
        }

    }
}
class Banner {
    private Long id;
    private Integer orders;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getOrders() {
        return orders;
    }
    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public Banner(Long id, Integer orders) {
        this.id = id;
        this.orders = orders;
    }
}
