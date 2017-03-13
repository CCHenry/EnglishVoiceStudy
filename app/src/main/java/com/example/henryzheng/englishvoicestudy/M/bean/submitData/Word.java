/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.englishvoicestudy.M.bean.submitData;


import cn.bmob.v3.BmobObject;

/**
 * Auto-generated: 2017-02-13 11:46:17
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Word extends BmobObject {
    private Integer id;
    private String word;
    private String trans;
    private String phonetic;
    private String tags;
    private String progress;
    public void setWord(String word) {
         this.word = word;
     }
     public String getWord() {
         return word;
     }

    public void setTrans(String trans) {
         this.trans = trans;
     }
     public String getTrans() {
         return trans;
     }

    public void setPhonetic(String phonetic) {
         this.phonetic = phonetic;
     }
     public String getPhonetic() {
         return phonetic;
     }

    public void setTags(String tags) {
         this.tags = tags;
     }
     public String getTags() {
         return tags;
     }

    public void setProgress(String progress) {
         this.progress = progress;
     }
     public String getProgress() {
         return progress;
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}