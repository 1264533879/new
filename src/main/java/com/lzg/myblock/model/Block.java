package com.lzg.myblock.model;

import com.lzg.myblock.utils.StringUtil;

import java.util.Date;

public class Block {
    public String hash;//自己的hash值
    public String previousHash;//保存上一个块的hash值
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash=calculateHash();
    }


    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    /**
     * 挖矿方法
     * @param difficulty
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring(0,difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
            System.out.println(hash);
        }
        System.out.println("挖到了！！！！ hash值为 : " + hash);
    }
}
