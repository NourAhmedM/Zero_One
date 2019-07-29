package com.example.android.secrethands;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextInputLayout mEmailAuto;
    private TextInputLayout mPasswordAuto;
    private Button mLoginButton;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        firebaseAuth=FirebaseAuth.getInstance();
        //UI Refrence

        mEmailView = (AutoCompleteTextView)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        mEmailAuto = (TextInputLayout) findViewById(R.id.email_auto);
        mPasswordAuto =(TextInputLayout)findViewById(R.id.password_auto);
        mLoginButton=(Button)findViewById(R.id.login_button);
        progressBar=(ProgressBar)findViewById(R.id.progress_login);
        signup=(TextView) findViewById(R.id.sign_up_text_view);
        progressBar.setVisibility(View.GONE);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordAuto.setError(null);
                mEmailAuto.setError(null);
                String password = mPasswordView.getText().toString();
                String email = mEmailView.getText().toString();
                if(!email.contains("@")){
                    mEmailAuto.setError("Invalid Email Adress");
                    return;

                }
                if(password.length()<8){
                    mPasswordAuto.setError("Invalid Password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //    getActivity().onBackPressed();



                                } else {
                                    Toast.makeText(Signin.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    // If sign in fails, display a message to the user.

                                }

                                // ...
                            }
                        });

            }
        });

    }
}
