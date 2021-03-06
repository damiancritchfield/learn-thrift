package org.lscode.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.lscode.thrift.client.service.HelloWorldService;

/**
 * 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
 */
public class TNonblockingHelloServiceClient {

    public void startClient(String username){
        TTransport tTransport = null;
        try {
            tTransport = new TFramedTransport(new TSocket("localhost",8888,30000));
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(tTransport);
//            TProtocol protocol = new TCompactProtocol(tTransport);
//            TProtocol protocol = new TJSONProtocol(tTransport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            tTransport.open();
            String result = client.sayHello(username);
            System.out.println("Thrify client result = " + result);
        } catch (TTransportException ex) {
            ex.printStackTrace();
        } catch (TException ex) {
            ex.printStackTrace();
        }finally {
            if(tTransport != null){
                tTransport.close();
            }
        }
    }

    public static void main(String[] args){
        TNonblockingHelloServiceClient client = new TNonblockingHelloServiceClient();
        client.startClient(" world");
    }

}
