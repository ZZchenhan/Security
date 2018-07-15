package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class GuardUserVo implements Cloneable{
    private String certificateNo;
    private Integer guardId;
    private Integer qualificationId;

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Integer getGuardId() {
        return guardId;
    }

    public void setGuardId(Integer guardId) {
        this.guardId = guardId;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GuardUserVo o = (GuardUserVo) super.clone();
        return o;
    }
}
