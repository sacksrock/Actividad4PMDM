package com.example.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public EditText etUsername;
    public EditText etPass;
    public Button btnLogin;
    public Button btnRegister;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;


    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        //asignacion de elementos
        etUsername=v.findViewById(R.id.etusername);
        etPass=v.findViewById(R.id.etpass);
        btnLogin=v.findViewById(R.id.btnlogin);
        btnRegister=v.findViewById(R.id.btnregister);
        //inicializacion de events
        events = new LoginFragmentEvents(this);
        //asignacion de controlador de eventos a los botones
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);


        return v;
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
