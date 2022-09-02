import com.alibaba.fastjson.JSON;
import com.bowen.CmsProduct;
import com.bowen.Product;

public class LongTest {
    public static void main(String[] args) {
        Product p = new Product();
        p.setCompanyId(0L);
        p.setName("感冒");
        String s = JSON.toJSONString(p);
        System.out.println(s);
        CmsProduct cp = new CmsProduct();
        cp.setName(p.getName());
        cp.setCompanyId(p.getCompanyId());
        String s2 = JSON.toJSONString(cp);
        System.out.println(s2);
        System.out.println(cp.getCompanyId());
        if (cp.getCompanyId() != 0) {
            System.out.println("companyId");
        }


    }
}