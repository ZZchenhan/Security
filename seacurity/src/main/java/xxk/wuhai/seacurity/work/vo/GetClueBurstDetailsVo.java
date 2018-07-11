package xxk.wuhai.seacurity.work.vo;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 15:25
 */
public class GetClueBurstDetailsVo {
    private int clueBurstId;

    public GetClueBurstDetailsVo(int clueBurstId) {
        this.clueBurstId = clueBurstId;
    }

    public int getClueBurstId() {
        return clueBurstId;
    }

    public void setClueBurstId(int clueBurstId) {
        this.clueBurstId = clueBurstId;
    }
}
