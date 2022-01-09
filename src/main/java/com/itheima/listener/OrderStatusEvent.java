package com.itheima.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 订单状态变更事件
 * @author : ylj
 * create at:  2022/1/9
 */
public class OrderStatusEvent extends ApplicationEvent {

    @Setter
    @Getter
    private String eventInfo;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OrderStatusEvent(Object source) {
        // 事件发生源的引用
        super(source);
    }


}