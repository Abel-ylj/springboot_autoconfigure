package com.itheima.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : ylj
 * create at:  2022/1/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void sentEventTest() {
        OrderStatusEvent orderStatusEvent = new OrderStatusEvent(this.getClass().getSimpleName());
        orderStatusEvent.setEventInfo("这是个测试事件");
        applicationContext.publishEvent(orderStatusEvent);
    }
}