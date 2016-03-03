package com.bssys.nio2.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.bssys.nio2.SessionState;

public class ServerReadCompletionHandler implements CompletionHandler<Integer, SessionState> {

	private AsynchronousSocketChannel socketChannel;
	private ByteBuffer inputBuffer;

	public ServerReadCompletionHandler(AsynchronousSocketChannel socketChannel, ByteBuffer inputBuffer) {
		this.socketChannel = socketChannel;
		this.inputBuffer = inputBuffer;
	}

	@Override
	public void completed(Integer bytesRead, SessionState sessionState) {

		byte[] buffer = new byte[bytesRead];
		inputBuffer.rewind();

		inputBuffer.get(buffer);
		String message = new String(buffer);

		System.out.println("Received message from client : " + message);
		
		ServerWriteCompletionHandler writeCompletionHandler = new ServerWriteCompletionHandler(socketChannel);
		
		ByteBuffer outputBuffer = ByteBuffer.wrap(buffer);
		socketChannel.write(outputBuffer, sessionState, writeCompletionHandler);
	}

	@Override
	public void failed(Throwable exc, SessionState attachment) {
		// Handle read failure.....
	}
}
