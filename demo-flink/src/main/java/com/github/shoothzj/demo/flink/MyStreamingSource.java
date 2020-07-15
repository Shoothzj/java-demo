package com.github.shoothzj.demo.flink;

import com.github.shoothzj.demo.flink.module.Item;
import com.github.shoothzj.javatool.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class MyStreamingSource implements SourceFunction<Item> {

    private boolean isRunning = true;

    /**
     * 重写run方法产生一个源源不断的数据发送源
     * @param ctx
     * @throws Exception
     */
    @Override
    public void run(SourceContext<Item> ctx) throws Exception {
        while (isRunning) {
            Item item = generateItem();
            ctx.collect(item);

            CommonUtil.sleep(TimeUnit.SECONDS, 1);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    private Item generateItem() {
        int i = new Random().nextInt(100);

        Item item = new Item();
        item.setName("name" + i);
        item.setId(i);
        return item;
    }

}
