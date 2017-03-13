/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.englishvoicestudy.M.bean.submitData;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Auto-generated: 2017-02-13 11:46:17
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Wordbook extends BmobObject{

    private List<Word> item;
    public void setItem(List<Word> item) {
         this.item = item;
     }
     public List<Word> getItem() {
         return item;
     }

}