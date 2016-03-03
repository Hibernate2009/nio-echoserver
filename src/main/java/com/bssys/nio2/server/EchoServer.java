package com.bssys.nio2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bssys.nio2.SessionState;

public class EchoServer {

	public void run() throws IOException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(newFixedThreadPool);

		final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open(group);
		InetSocketAddress address = new InetSocketAddress("localhost", 3333);
		listener.bind(address);

		AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(listener);

		SessionState state = new SessionState();
		listener.accept(state, acceptCompletionHandler);
		
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Async server started");
		new EchoServer().run();
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
