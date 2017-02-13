package com.captain.supermarket.manage.dao;

import com.captain.supermarket.manage.vo.Report;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午10:16
 */
public class test {
    public static void main(String[] args) {
        String s = "200 $";
        System.out.println(Double.parseDouble(s.substring(0,s.indexOf("$")-2)));
    }
}
