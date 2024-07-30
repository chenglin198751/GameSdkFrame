package com.game.sdk;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * SDK用户参数，uid,token是我司SDK平台自身赋值
 */
@Keep
public class UserParams {
    public boolean isSuccess;
    public String token; //我司平台token,非第三方
    public String uid; //我司平台uid,非第三方
    public String sign;
    public String timestamp;
    public String picture;
    public String nickname;
    public String mobile;
    public String email;
    public String channel;
    public String adId;
    public String error;//错误信息

    public void clear() {
        isSuccess = false;
        token = null;
        uid = null;
        sign = null;
        timestamp = null;
        picture = null;
        nickname = null;
        mobile = null;
        email = null;
        channel = null;
        adId = null;
        error = null;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserParams{" +
                "isSuccess=" + isSuccess +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", picture='" + picture + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", channel='" + channel + '\'' +
                ", adId='" + adId + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
