package com.bssys.nio2.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.bssys.nio2.SessionState;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, SessionState> {

	private AsynchronousServerSocketChannel listener;

	public AcceptCompletionHandler(AsynchronousServerSocketChannel listener) {
		this.listener = listener;
	}

	@Override
	public void completed(AsynchronousSocketChannel socketChannel, SessionState sessionState) {
		// accept the next connection
		SessionState newSessionState = new SessionState();
		listener.accept(newSessionState, this);

		// handle this connection
		ByteBuffer inputBuffer = ByteBuffer.allocate(2048);
		ServerReadCompletionHandler readCompletionHandler = new ServerReadCompletionHandler(socketChannel, inputBuffer);
		socketChannel.read(inputBuffer, sessionState, readCompletionHandler);
	}

	@Override
	public void failed(Throwable exc, SessionState sessionState) {
		// Handle connection failure...
	}
}
