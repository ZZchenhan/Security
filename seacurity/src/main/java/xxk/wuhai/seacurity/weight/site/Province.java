package xxk.wuhai.seacurity.weight.site;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class Province extends Area{
    private int provinceId;

    private String provinceName;

    public Province() {
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return provinceName;
    }
}
