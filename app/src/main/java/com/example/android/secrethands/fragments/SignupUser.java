package com.example.android.secrethands.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupUser extends Fragment {
    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mAgeView;
    private EditText mPasswordView;
    private AutoCompleteTextView mUserNameView;
    private TextInputLayout mUserNmaeAuto;
    private TextInputLayout mEmailAuto;
    private TextInputLayout mAgeAuto;
    private TextInputLayout mPasswordAuto;
    private Button mCreateAccountButton;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonMale;
    private RadioButton mRadioButtonFemale;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;



    public SignupUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_signup_user, container, false);
        //initialize Firebase
        firebaseAuth= FirebaseAuth.getInstance();
        //Bind UI references
        mUserNameView = (AutoCompleteTextView)rootView.findViewById(R.id.user_name);
        mUserNmaeAuto = (TextInputLayout)rootView.findViewById(R.id.user_name_auto);
        progressBar=(ProgressBar)rootView.findViewById(R.id.progress_user);
        progressBar.setVisibility(View.GONE);
        mCreateAccountButton = (Button)rootView.findViewById(R.id.create_account_user_button);
        mEmailView = (AutoCompleteTextView)rootView.findViewById(R.id.email_user);
        mPasswordView = (EditText)rootView.findViewById(R.id.password_user);
        mEmailAuto = (TextInputLayout) rootView.findViewById(R.id.email_auto_user);
        mPasswordAuto =(TextInputLayout)rootView.findViewById(R.id.password_auto_user);
        mAgeView=(AutoCompleteTextView)rootView.findViewById(R.id.age_user);
        mAgeAuto=(TextInputLayout)rootView.findViewById(R.id.age_auto_user);
        mRadioGroup=(RadioGroup)rootView.findViewById(R.id.radio_user);
        mRadioButtonMale=(RadioButton)rootView.findViewById(R.id.male);
        mRadioButtonFemale=(RadioButton)rootView.findViewById(R.id.female);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordAuto.setError(null);
                mAgeAuto.setError(null);
                mUserNmaeAuto.setError(null);
                mEmailAuto.setError(null);
                String password = mPasswordView.getText().toString();
                String age = mAgeView.getText().toString();
                String email = mEmailView.getText().toString();
                final String userName = mUserNameView.getText().toString();
                final String gender;
                if(!email.contains("@")){
                    mEmailAuto.setError("Invalid Email Adress");
                    return;

                }
                if(password.length()<8){
                    mPasswordAuto.setError("Password have to be more than 8 char");
                    return;
                }
                if(userName.length()<=3 || userName.length()>=20){
                    mUserNmaeAuto.setError("Username have to be more than 3 and less than 20 char");
                    return;
                }
                if(age.isEmpty()){
                    mAgeAuto.setError("Please Insert Age");
                    return;
                }

                int userAge= 20;
                try {
                    userAge=Integer.parseInt(age);

                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if(userAge<5||userAge>110){
                    mAgeAuto.setError("Invalid Age ");
                    return;
                }
                if (mRadioGroup.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getContext(),"Please Choose Gender",Toast.LENGTH_SHORT).show();
                    return;
                    // no radio buttons are checked
                }
                if(mRadioButtonFemale.isSelected()){
                    gender="Female";

                }else{
                    gender="Male";
                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Patient patient=new Patient(Integer.parseInt(mAgeView.getText().toString()),gender,userName,1);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(patient);
                            //          getActivity().onBackPressed();

                        }else{
                            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });







            }
        });
        return rootView;
    }

}
