package xxk.wuhai.seacurity.weight.site;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class District extends Area{
    private int districtId;
    private String districtName;

    public District() {
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return districtName;
    }
}
