package com.bssys.nio2.server;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.bssys.nio2.SessionState;

public class ServerWriteCompletionHandler implements CompletionHandler<Integer, SessionState> {

	private AsynchronousSocketChannel socketChannel;
	

	public ServerWriteCompletionHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void completed(Integer bytesWritten, SessionState sessionState) {
		try {
			socketChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void failed(Throwable exc, SessionState attachment) {
		// Handle write failure.....
	}

}
