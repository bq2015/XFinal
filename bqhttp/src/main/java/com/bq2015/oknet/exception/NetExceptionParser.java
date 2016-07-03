package com.bq2015.oknet.exception;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.bq2015.oknet.utils.Cons;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class NetExceptionParser extends ExceptionParser {


    @Override
    protected boolean handler(@NonNull Throwable e, @NonNull IHandler handler) {
        String s = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
        if (Cons.IO_EXCEPTION.equalsIgnoreCase(s) || Cons.SOCKET_EXCEPTION.equalsIgnoreCase(s)) {
            //cancel request
            return true;
        }
        if (UnknownHostException.class.isAssignableFrom(e.getClass()) ||
                SocketException.class.isAssignableFrom(e.getClass()) ||
                SocketTimeoutException.class.isAssignableFrom(e.getClass())) {
            handler.onHandler(Cons.Error.NetWork, s);
            return true;
        }
        return false;
    }
}

