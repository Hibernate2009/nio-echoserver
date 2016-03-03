package com.bssys.nio2.client;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.bssys.nio2.SessionState;

public class ClientReadCompletionHandler implements CompletionHandler<Integer, SessionState>{
	
	private AsynchronousSocketChannel socketChannel;
	private ByteBuffer inputBuffer;
	
	
	public ClientReadCompletionHandler(AsynchronousSocketChannel socketChannel,  ByteBuffer inputBuffer) {
		this.socketChannel = socketChannel;
		this.inputBuffer = inputBuffer;
		
	}

	@Override
	public void completed(Integer bytesRead, SessionState sessionState) {
		byte[] buffer = new byte[bytesRead];
		inputBuffer.rewind();
		// Rewind the input buffer to read from the beginning

		inputBuffer.get(buffer);
		String message = new String(buffer);

		System.out.println("Received message from server : " + message);
		
	}

	@Override
	public void failed(Throwable exc, SessionState sessionState) {
		// TODO Auto-generated method stub
		
	}

}
