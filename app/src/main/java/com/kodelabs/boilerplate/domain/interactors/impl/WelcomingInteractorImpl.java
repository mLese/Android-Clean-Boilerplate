package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.WelcomingInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.domain.repository.MessageRepository;

public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomingInteractor {

    WelcomingInteractor.Callback mCallback;
    MessageRepository mMessageRepository;

    public WelcomingInteractorImpl(Executor threadExecutor, MainThread mainThread, WelcomingInteractor.Callback callback, MessageRepository messageRepository)
    {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mMessageRepository = messageRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with");
            }
        });
    }

    private void postMessage(final String msg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(msg);
            }
        });
    }

    @Override
    public void run() {
        final String message = mMessageRepository.getWelcomeMessage();

        if (message == null || message.length() == 0) {
            notifyError();
            return;
        }

        postMessage(message);
    }
}
