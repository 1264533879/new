package com.lzg.myblock;

import com.google.gson.GsonBuilder;
import com.lzg.myblock.model.Block;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class MyblockApplication {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty=3;//挖矿难度

    public static void main(String[] args) {
        SpringApplication.run(MyblockApplication.class, args);
       // NoobChain();
        reallyFrenzy();
    }

    /**
     * 矿区
     */
    public static void reallyFrenzy(){
        long startTime=System.currentTimeMillis();
        for (int i = 0; i <1000 ; i++) {
            blockchain.add(new Block("我是第"+(i+1)+"块矿",((i==0)?i:blockchain.get(blockchain.size()-1).hash)+""));
            System.out.println("开始挖矿计算中......."+(i+1));
            blockchain.get(i).mineBlock(difficulty);
        }
        System.out.println("验证结果中...."+isChainValid());
        long endTime=System.currentTimeMillis();
        System.out.println("难度为："+difficulty);
        System.out.println("总共耗时："+((endTime-startTime)/1000)+"秒");
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("JSON结果：");
        System.out.println(blockchainJson);
    }
    /**
     * 测试方法
     */
    public static void NoobChain(){
        long startTime;
        long endTime;
        blockchain.add(new Block("我是---创世块", "0"));
        System.out.println("开始挖矿--计算中.....1");
        startTime = System.currentTimeMillis();
        blockchain.get(0).mineBlock(difficulty);
        endTime=System.currentTimeMillis();
        long oneTime=endTime-startTime;


        blockchain.add(new Block("我是---第二块",blockchain.get(blockchain.size()-1).hash));
        System.out.println("开始挖矿--计算中.....2");
        startTime = System.currentTimeMillis();
        blockchain.get(1).mineBlock(difficulty);
        endTime=System.currentTimeMillis();
        long towTime=endTime-startTime;


        blockchain.add(new Block("我是---第三块",blockchain.get(blockchain.size()-1).hash));
        System.out.println("开始挖矿--计算中.....3");
        startTime = System.currentTimeMillis();
        blockchain.get(2).mineBlock(difficulty);
        endTime=System.currentTimeMillis();
        long threeTime=endTime-startTime;

        System.out.println("验证结果中...."+isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("难度为："+difficulty);
        System.out.println("总共耗时:"+(oneTime+towTime+threeTime)+"ms");
        System.out.println("JSON结果：");
        System.out.println(blockchainJson);
    }


    /**
     * 用来检验区块链的完整性
     * @return
     */
    public static Boolean isChainValid() {
        Block currentBlock;//用来保存当前块对象
        Block previousBlock;//保存上一个块对象
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);//获取当前块的对象
            previousBlock = blockchain.get(i - 1);//获取上一个块的对象

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("以保存的hash值和当前生成的不一样");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("哈希值不匹配");
                return false;
            }

        }
        return true;
    }
}
