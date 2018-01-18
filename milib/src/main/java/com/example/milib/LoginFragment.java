package com.example.milib;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public EditText etUsername;
    public EditText etPass;
    public Button btnLogin;
    public Button btnRegister;
    public Button login_button;
    TwitterLoginButton login_Tbutton;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;
    CallbackManager callbackManager;


    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Twitter.initialize(getActivity());
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        callbackManager = CallbackManager.Factory.create();
        //asignacion de elementos

        etUsername=v.findViewById(R.id.etusername);
        etPass=v.findViewById(R.id.etpass);
        btnLogin=v.findViewById(R.id.btnlogin);
        btnRegister=v.findViewById(R.id.btnregister);
        login_button=v.findViewById(R.id.login_button);
        login_Tbutton=v.findViewById(R.id.login_Tbutton);
        //login_button.setReadPermissions("email");

        //inicializacion de events
        events = new LoginFragmentEvents(this);
        //asignacion de controlador de eventos a los botones
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);
        login_button.setOnClickListener(events);
        login_Tbutton.setOnClickListener(events);


        login_Tbutton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

       /* TwitterConfig config = new TwitterConfig.Builder(getActivity())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("SQWuXKhwIFbY6lWjphuNVWrPo", "0Kyn5VxcbR1Id0kg15iLNVar4sa3U5bkpXckXDcu3MmqK0n9HR"))
                .debug(true)
                .build();
        Twitter.initialize(config);
*/
        return v;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listener.cambiarPantalla();

    }
}

class LoginFragmentEvents implements View.OnClickListener{

    private LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment fragment){
        this.loginFragment = fragment;

    }

    @Override
    public void onClick(View view) {
    if(view.getId()==this.loginFragment.btnLogin.getId()){
        this.loginFragment.listener.loginFragmentLoginButtonClicked(this.loginFragment.etUsername.getText().toString(),this.loginFragment.etPass.getText().toString());
    }
    else if(view.getId()==this.loginFragment.btnRegister.getId()){
        this.loginFragment.listener.loginFragmentRegisterButtonClicked();
    }


    }
}
