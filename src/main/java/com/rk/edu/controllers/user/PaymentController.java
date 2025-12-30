package com.rk.edu.controllers.user;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.rk.edu.model.OrderEntity;
import com.rk.edu.services.user.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public OrderEntity createPayment(
            @RequestParam Long userId,
            @RequestParam Long productId
    ) throws RazorpayException {
        return paymentService.createOrder(userId, productId);
    }

    @PostMapping("/verify")
    public void verify(@RequestParam String razorpayOrderId) {
        paymentService.verifyPayment(razorpayOrderId);
    }
}

