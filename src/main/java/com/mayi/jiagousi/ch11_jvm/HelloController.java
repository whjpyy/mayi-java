package com.mayi.jiagousi.ch11_jvm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class HelloController {

    private static AtomicInteger count = new AtomicInteger(0);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello, " + count.incrementAndGet());
        return "hello";
    }
    // 测试串行吞吐量  启动28次GC 吞吐量 1182.0/sec
    /**
     * -XX:+PrintGCDetails -Xms32m -Xmx32m
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:+UseSerialGC
     * -XX:PermSize=32m
     */
    // 扩大堆内存 2次GC   吞吐量 1340.5/sec
    /**
     * -XX:+PrintGCDetails -Xms512m -Xmx512m
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:+UseSerialGC
     * -XX:PermSize=32m
     */
    // 并行堆内存
    /**
     * -XX:+PrintGCDetails -Xms512m -Xmx512m
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:+UseParNewGC
     * -XX:PermSize=32m
     */
    // 并行合并回收 吞吐量1700/sec
    /**
     * -XX:+PrintGCDetails -Xms512m -Xmx512m
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:+UseParallelGC
     * -XX:+UseParallelOldGC
     * -XX:ParallelGCThreads=8
     * -XX:PermSize=32m
     */
}
