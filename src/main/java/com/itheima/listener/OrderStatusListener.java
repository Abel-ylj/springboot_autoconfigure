package com.itheima.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件状态监听器
 * @author : ylj
 * create at:  2022/1/9
 */
@Component
public class OrderStatusListener implements ApplicationListener<OrderStatusEvent> {

    /**
     * Handle an application event
     * @param event 监听到的事件, 实际上是有人将时间发给了多播器, 多播器根据事件类型通知注册的监听器.
     */
    @Override
    public void onApplicationEvent(OrderStatusEvent event) {
        System.out.println("事件源:" + event.getSource());
        System.out.println("发生时间时间点:" + event.getTimestamp());
        System.out.println("事件描述:" + event.getEventInfo());
    }

}