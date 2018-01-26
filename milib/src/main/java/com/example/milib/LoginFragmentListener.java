package com.example.milib;

import android.support.v4.app.FragmentActivity;

import com.facebook.AccessToken;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by JuanmaJR on 10/12/2017.
 */

public interface LoginFragmentListener {
    public void loginFragmentLoginButtonClicked(String sUser, String sPass);
    public void loginFragmentRegisterButtonClicked();
    public void cambiarPantalla();
    public void loginFragmentLoginFacebook( FragmentActivity fragmentActivity, AccessToken accessToken);
    public void handleTwitterSession( FragmentActivity fragmentActivity,TwitterSession session);

}
