package com.rest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAutherisation {

    public static void main(String[] args) {
        String userNameColonPassword="MyUsername:Mypassword23";

        String base64Encoder = Base64.getEncoder().encodeToString(userNameColonPassword.getBytes(StandardCharsets.UTF_8));

        System.out.println("encoded  ="+base64Encoder);

        byte[] decodeByte = Base64.getDecoder().decode(base64Encoder);

        System.out.println("decode  ="+new String(decodeByte));

    }




}
