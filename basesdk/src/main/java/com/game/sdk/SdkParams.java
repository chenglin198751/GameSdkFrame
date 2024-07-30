package com.game.sdk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;


public final class SdkParams {
    public static String TAG = "easy_sdk";
    public static final UserParams userParams = new UserParams();

    public final static class LoginCode {
        public static final int LOGIN_SUCCESS = 0;
        public static final int LOGIN_UNSUPPORTED = -1;
        public static final int LOGIN_FAILED = -2;
        public static final int LOGIN_CANCEL = -3;
    }

    public final static class PayCode {
        public static final int PAY_SUCCESS = 0;
        public static final int PAY_FAILED = -1;
        public static final int PAY_CANCEL = -2;
    }

    public final static class Method {
        public static final String INIT = "init";
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String SWITCH_LOGIN = "switch_login";
        public static final String EXIT = "exit";
        public static final String PAY = "pay";
    }

    public final static class SdkPlat {
        public static final String VIVO = "vivo";
        public static final String OPPO = "oppo";
        public static final String HUAWEI = "huawei";
        public static final String QOO = "Qoo";
        public static final String AWIN = "awin";
    }

    @IntDef({LoginCode.LOGIN_SUCCESS, LoginCode.LOGIN_UNSUPPORTED, LoginCode.LOGIN_FAILED, LoginCode.LOGIN_CANCEL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginType {
    }

    @IntDef({PayCode.PAY_SUCCESS, PayCode.PAY_CANCEL, PayCode.PAY_FAILED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PayType {
    }

    @StringDef({Method.INIT, Method.LOGIN, Method.LOGOUT, Method.SWITCH_LOGIN, Method.EXIT, Method.PAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MethodType {
    }
}
