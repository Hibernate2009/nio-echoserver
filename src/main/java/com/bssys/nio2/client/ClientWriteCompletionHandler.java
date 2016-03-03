package com.bssys.nio2.client;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.bssys.nio2.SessionState;

public class ClientWriteCompletionHandler implements CompletionHandler<Integer, SessionState> {

	private AsynchronousSocketChannel socketChannel;

	public ClientWriteCompletionHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void completed(Integer result, SessionState sessionState) {
		// TODO Auto-generated method stub
		ByteBuffer inputBuffer = ByteBuffer.allocate(2048);
		ClientReadCompletionHandler clientReadCompletionHandler = new ClientReadCompletionHandler(socketChannel,
				inputBuffer);
		socketChannel.read(inputBuffer, sessionState, clientReadCompletionHandler);
	}

	@Override
	public void failed(Throwable exc, SessionState attachment) {
		// TODO Auto-generated method stub

	}

}
