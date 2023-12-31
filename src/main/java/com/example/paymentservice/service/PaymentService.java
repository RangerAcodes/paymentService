package com.example.paymentservice.service;

import com.example.paymentservice.paymentgateways.PaymentGateway;
import com.example.paymentservice.paymentgateways.PaymentGatewayStrategyChooser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayStrategyChooser paymentGatewayChooserStrategy;

    public PaymentService(PaymentGatewayStrategyChooser paymentGatewayChooserStrategy) {
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }

    public String initiatePayment(String orderId, String email, String phoneNumber, Long amount) {
        // Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();
        // double amount = 10.10; // store number * 100
        // String orderId, String email, String phoneNumber, Long amount
//        Long amount = 1010L; // 10.00 => 1000

        PaymentGateway paymentGateway =
                paymentGatewayChooserStrategy.getBestPaymentGateway();

        return paymentGateway.generatePaymentLink(orderId, email, phoneNumber, amount);
    }
}
