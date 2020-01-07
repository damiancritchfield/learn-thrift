package org.lscode.thrift.server.service.impl;

import org.apache.thrift.TException;
import org.lscode.thrift.server.service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    public String sayHello(String username) throws TException {
        return "hello, your username is " + username;
    }
}
