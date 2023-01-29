package com.management.events.controllers;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println(DigestUtils.sha1Hex("admin"));
    }
}
