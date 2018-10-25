package com.baizhi.cmfz.utils;

import java.util.*;

import redis.clients.jedis.Jedis;



public class RedisUtils {
   /* private static final Jedis jedis = new Jedis("192.168.136.132",7000);
    public  static void   setParameters(String key,String field,String value) {
        //连接本地的 Redis 服务

        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());


        //设置 redis 字符串数据

        jedis.hset(key,field,value);

        // 关闭连接
        jedis.close();
        // 获取存储的数据并输出
        // System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));


        //存储数据到列表中
        //jedis.lpush("site-list", "Runoob");
        //jedis.lpush("site-list", "Google");
        // jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
       *//* List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }*//*

        // 获取数据并输出
      *//*  Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }*//*
    }
    public static Map<String,String> getParameters(String name){

        //查看服务是否运行
       // System.out.println("服务正在运行: "+jedis.ping());


        //设置 redis 字符串数据
        Map<String, String> map = jedis.hgetAll(name);

        // 关闭连接
        jedis.close();
        return map;
    }
    public static void deleteAll(){

        jedis.flushAll();
    }
    public static Map<String,String> getKeys(){
        Map<String, String> map = jedis.hgetAll("stu");
        return map;
    }*/


}
