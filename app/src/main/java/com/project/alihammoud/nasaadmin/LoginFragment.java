package com.project.alihammoud.nasaadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button login;
    private EditText username, password, ipAddress;
    public  String user, pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_login, container, false);

        username = view.findViewById(R.id.edtUsername);
        password = view.findViewById(R.id.edtPassword);
        //ipAddress = view.findViewById(R.id.edtIP);

        login = view.findViewById(R.id.login);
        login.setOnClickListener(this);


        return view;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login:


                if (!validateForm()) {
                    return;
                }

                user = username.getText().toString();
                pass = password.getText().toString();
                //ip = ipAddress.getText().toString().trim();


                if(user.equals("stanleyjobson") & pass.equals("swordfish")){

                    Toast.makeText(getContext(), "Logged in as an Admin", Toast.LENGTH_LONG).show();
                   /* Bundle bundle = new Bundle();
                    bundle.putString("username",username.getText().toString());

                    Fragment Main = new MainFragment();
                    Main.setArguments(bundle);*/
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, new MainFragment());
                    ft.commit();
                    break;

                }
                else{

                    Toast.makeText(getContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                }

        }
    }

    private boolean validateForm() {
        boolean valid = true;

        if (  username.getText().toString().trim().isEmpty()){
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        if (  password.getText().toString().trim().isEmpty()){
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}