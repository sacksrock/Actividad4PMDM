package com.example.juanmajr.flclase;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


}
