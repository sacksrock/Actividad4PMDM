package com.example.juanmajr.flclase;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.juanmajr.flclase.FBObjects.Contact;
import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.facebook.AccessToken;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Twitter.initialize(this);
        //inicializado el fragment del Login
        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);

        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);

        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(loginFragment);
        transition.hide(registerFragment);
        transition.commit();

        DatabaseHandler db = new DatabaseHandler(this);

        db.addContact(new Contact("Daniel", "61144881111"));
        db.addContact(new Contact("uka", "99874536"));
        db.addContact(new Contact("jordi", "69784518541"));
        db.addContact(new Contact("Yony", "666"));

        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("TUTORIALSQLLITE ", log);
        }

        FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        FirebaseCrash.log("Activity created");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser,sPass,mainActivity);
    }

    @Override
    public void loginFragmentRegisterButtonClicked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(mainActivity.loginFragment);
        transition.show(mainActivity.registerFragment);
        transition.commit();

    }

    @Override
    public void registerFragmentBtnAceptarClicked(String sUser, String sPass) {
        DataHolder.instance.fireBaseAdmin.registerConEmailYPassword(sUser,sPass,mainActivity);
    }

    @Override
    public void registerFragmentBtnCancelarClciked() {
        FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(mainActivity.loginFragment);
        transition.hide(mainActivity.registerFragment);
        transition.commit();

    }

    @Override
    public void FireBaseAdmin_RegisterOk(Boolean ok) {
        if(ok){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }

    }

    @Override
    public void FireBaseAdmin_LoginOk(Boolean ok) {
        if(ok){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }
        else{

        }
    }

    @Override
    public void FireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

    }



    public void cambiarPantalla(){
        Intent intent = new Intent(mainActivity,SecondActivity.class);
        mainActivity.startActivity(intent);
        mainActivity.finish();
    }

    @Override
    public void loginFragmentLoginFacebook(FragmentActivity fragmentActivity,AccessToken accessToken) {
        DataHolder.instance.fireBaseAdmin.handleFacebookAccessToken(fragmentActivity,accessToken);
    }

    @Override
    public void handleTwitterSession(FragmentActivity fragmentActivity, TwitterSession session) {
        DataHolder.instance.fireBaseAdmin.handleTwitterSession(fragmentActivity, session);
    }


}
