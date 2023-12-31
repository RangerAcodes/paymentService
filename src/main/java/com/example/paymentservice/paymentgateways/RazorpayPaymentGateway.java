package com.example.paymentservice.paymentgateways;

import com.razorpay.*;
import org.json.JSONObject;

import com.razorpay.RazorpayClient;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {
    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    private RazorpayClient razorpayClient;

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        try {

            RazorpayClient razorpay = new RazorpayClient("rzp_test_M29J0oa7vzoTVr", "JGQ98xMBS6LafQNTRP50p8dB");

            JSONObject paymentLinkRequest = new JSONObject();

            paymentLinkRequest.put("amount", 1000);

            paymentLinkRequest.put("currency", "INR");

            paymentLinkRequest.put("accept_partial", true);

            paymentLinkRequest.put("first_min_partial_amount", 100);

            paymentLinkRequest.put("expire_by", 1704062338);

            paymentLinkRequest.put("reference_id", "TS1989");

            paymentLinkRequest.put("description", "Payment for policy no #23456");

            JSONObject customer = new JSONObject();

            customer.put("name", "+919000090000");

            customer.put("contact", "Adit Kumar");

            customer.put("email", "gaurav.kumar@example.com");

            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();

            notify.put("sms", true);

            notify.put("email", true);

            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("reminder_enable", true);

            JSONObject notes = new JSONObject();

            notes.put("policy_name", "Jeevan Bima");

            paymentLinkRequest.put("notes", notes);

            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");

            paymentLinkRequest.put("callback_method", "get");


            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            return payment.get("short_url").toString();


        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }
}