package xxk.wuhai.seacurity.contact.vo;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class ApiGetDirectoryVo {
    private int deptId;
    private String name;

    public ApiGetDirectoryVo(int deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
