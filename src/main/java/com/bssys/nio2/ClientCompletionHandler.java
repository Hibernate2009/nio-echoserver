package com.bssys.nio2;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ClientCompletionHandler implements CompletionHandler<Void, SessionState>{
	
	private AsynchronousSocketChannel socketChannel;
	
	public ClientCompletionHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void completed(Void result, SessionState sessionState) {
		// TODO Auto-generated method stub
		
		ByteBuffer wrap = ByteBuffer.wrap("test".getBytes());
		
		ClientWriteCompletionHandler clientWriteCompletionHandler = new ClientWriteCompletionHandler(socketChannel);
		socketChannel.write(wrap, sessionState, clientWriteCompletionHandler);
	}

	@Override
	public void failed(Throwable exc, SessionState sessionState) {
		// TODO Auto-generated method stub
		
	}

}
