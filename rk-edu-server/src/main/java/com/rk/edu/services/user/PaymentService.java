package com.rk.edu.services.user;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.rk.edu.enums.PaymentStatus;
import com.rk.edu.model.OrderEntity;
import com.rk.edu.model.Product;
import com.rk.edu.model.User;
import com.rk.edu.repositories.OrderRepository;
import com.rk.edu.repositories.ProductRepository;
import com.rk.edu.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public OrderEntity createOrder(Long userId, Long productId) throws RazorpayException {

    	User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));


        JSONObject options = new JSONObject();
        options.put("amount", product.getPrice() * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order razorOrder = razorpayClient.orders.create(options);

        OrderEntity order = new OrderEntity();
        order.setRazorpayOrderId(razorOrder.get("id"));
        order.setAmount(product.getPrice());
        order.setStatus(PaymentStatus.CREATED);
        order.setUser(user);
        order.setProduct(product);

        return orderRepo.save(order);
    }

    public void verifyPayment(String razorpayOrderId) {
        OrderEntity order = orderRepo
                .findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow();

        order.setStatus(PaymentStatus.SUCCESS);
        orderRepo.save(order);
    }
}

