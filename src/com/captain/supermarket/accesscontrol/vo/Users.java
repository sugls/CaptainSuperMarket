package com.captain.supermarket.accesscontrol.vo;

/**
 * 用户 vo
 * @author lsc
 *         createtime 2017年 02月 02日 星期四 下午8:50
 */
public class Users {
    private int userid;
    private String username;
    private String password;
    private int useridentity;

    public Users(int userid, String username, String password, int useridentity) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.useridentity = useridentity;
    }

    public Users() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUseridentity() {
        return useridentity;
    }

    public void setUseridentity(int useridentity) {
        this.useridentity = useridentity;
    }
}
