package com.rk.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(
            "rzp_test_RuN6bp4O2XRGoR",
            "YwEFFgdQgnyQipRf5ojZTRF5"
        );
    }
}

