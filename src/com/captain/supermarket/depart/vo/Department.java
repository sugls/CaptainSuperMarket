package com.captain.supermarket.depart.vo;

/** 部门 vo
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午12:32
 */
public class Department {
    private int departid;
    private String departname;
    private String description;

    public Department() {
    }

    public Department(int departid, String departname, String description) {
        this.departid = departid;
        this.departname = departname;
        this.description = description;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
