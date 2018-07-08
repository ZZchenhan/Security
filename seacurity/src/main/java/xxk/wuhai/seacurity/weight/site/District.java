package xxk.wuhai.seacurity.weight.site;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class District extends Area{
    private String districtId;
    private String districtName;

    public District() {
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
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
