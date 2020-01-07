package org.lscode.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.lscode.thrift.server.service.HelloWorldService;
import org.lscode.thrift.server.service.impl.HelloWorldServiceImpl;

public class HelloServiceServer {

    public void startServer(){
        try {
            System.out.println("HelloWorldServer start ... ");
            TServerSocket serverTransport = new TServerSocket(8888);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
//            tArgs.protocolFactory(new TCompactProtocol.Factory());
//            tArgs.protocolFactory(new TJSONProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        HelloServiceServer serviceServer = new HelloServiceServer();
        serviceServer.startServer();
    }

}
