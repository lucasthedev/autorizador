package com.authorizer.service.infrastructure.persistence.redis;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "PaymentData", timeToLive = 60L)
public class PaymentData {
    @Id
    private String id;

    private PaymentData(final String id){
        this.id = id;
    }

    public static PaymentData newPaymentData(String id){
        return new PaymentData(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
