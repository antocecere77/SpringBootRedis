package com.antocecere77.redis.queue;

public interface MessagePublisher {

    void publish(final String message);
}