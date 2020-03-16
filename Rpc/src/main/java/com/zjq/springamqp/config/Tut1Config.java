package com.zjq.springamqp.config;

import com.zjq.springamqp.message.Tut1Client;
import com.zjq.springamqp.message.Tut1Server;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut6","rpc"})
@Configuration
public class Tut1Config {

    @Profile("client")
    private static class ClientConfig {

//        @Bean
//        public DirectExchange exchange() {
//            return new DirectExchange("tut.rpc");
//        }

        @Bean
        public Tut1Client client() {
            return new Tut1Client();
        }

    }

    @Profile("server")
    private static class ServerConfig {

        @Bean
        public Queue queue() {
            return new Queue("tut.rpc.requests");
        }

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public Binding binding(DirectExchange exchange,
                               Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("rpc");
        }

        @Bean
        public Tut1Server server() {
            return new Tut1Server();
        }

    }
}