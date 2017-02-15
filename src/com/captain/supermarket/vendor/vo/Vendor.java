package com.captain.supermarket.vendor.vo;

/**
 * 供应商 vo
 * @author lsc
 *         createtime 2017年 02月 07日 星期二 下午1:25
 */
public class Vendor {
    private int vendorid;
    private String vendorname;
    private String vendoraddress;
    private String vendorphone;
    private String vendorfax;
    private String contact;

    public Vendor() {
    }

    public Vendor(int vendorid, String vendorname, String vendoraddress, String vendorphone, String vendorfax, String contact) {
        this.vendorid = vendorid;
        this.vendorname = vendorname;
        this.vendoraddress = vendoraddress;
        this.vendorphone = vendorphone;
        this.vendorfax = vendorfax;
        this.contact = contact;
    }

    public int getVendorid() {
        return vendorid;
    }

    public void setVendorid(int vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getVendorphone() {
        return vendorphone;
    }

    public void setVendorphone(String vendorphone) {
        this.vendorphone = vendorphone;
    }

    public String getVendorfax() {
        return vendorfax;
    }

    public void setVendorfax(String vendorfax) {
        this.vendorfax = vendorfax;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
