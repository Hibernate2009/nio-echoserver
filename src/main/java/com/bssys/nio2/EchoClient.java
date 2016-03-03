package com.bssys.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;

public class EchoClient {
	
	public void run() throws IOException{
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3333);
		client.connect(hostAddress, null, new ClientCompletionHandler(client)); 

	}

	public static void main(String[] args) throws IOException {
		new EchoClient().run();
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
