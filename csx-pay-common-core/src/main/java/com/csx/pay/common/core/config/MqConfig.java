package com.csx.pay.common.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author csx
 * @Package com.csx.pay.common.core.config
 * @Description: TODO
 * @date 2018/5/30 0030
 */
public class MqConfig {
    private static final Logger LOGGER=LoggerFactory.getLogger(MqConfig.class);

    /**
     * 商户通知队列
     */
    public static String MERCHANT_NOTIFY_QUEUE = "";

    /**
     * 订单通知队列
     */
    public static String ORDER_NOTIFY_QUEUE = "";

    private static Properties properties=null;

    static {
        if(properties==null){
            properties=new Properties();
        }
        try {
            InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("mq_config.properties");
            properties.load(is);
            init(properties);
        } catch (IOException e) {
            LOGGER.error("=== load and init mq exception:"+e);
        }
    }

    private static void init(Properties properties){
        MERCHANT_NOTIFY_QUEUE = properties.getProperty("tradeQueueName.notify");
        ORDER_NOTIFY_QUEUE = properties.getProperty("orderQueryQueueName.query");
    }
}
