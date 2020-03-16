package com.zjq.springamqp.config;

import com.zjq.springamqp.message.Tut1Receiver;
import com.zjq.springamqp.message.Tut1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut2", "work-queues"})
@Configuration
public class Tut1Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    //内部类
    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut1Receiver receiver1() {
            return new Tut1Receiver(1);
        }

        @Bean
        public Tut1Receiver receiver2() {
            return new Tut1Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}