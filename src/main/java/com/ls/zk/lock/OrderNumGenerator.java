package com.ls.zk.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerator {
    private static int count = 0;
    public String orderNumber(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpleDateFormat.format(new Date()) + "-" + ++count;
    }
}
