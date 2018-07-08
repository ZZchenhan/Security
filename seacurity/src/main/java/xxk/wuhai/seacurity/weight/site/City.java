package xxk.wuhai.seacurity.weight.site;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class City extends Area{
    private int cityId;

    private String cityName;

    public City() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
