package com.example.android.secrethands.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.secrethands.MainActivity;
import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupDoctor extends Fragment {
    //UI Refrence
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mAgeView;
    private AutoCompleteTextView mNationalIDView;
    private EditText mPasswordView;
    private AutoCompleteTextView mUserNameView;
    private TextInputLayout mUserNmaeAuto;
    private TextInputLayout mNationalIDAuto;
    private TextInputLayout mEmailAuto;
    private TextInputLayout mAgeAuto;
    private TextInputLayout mPasswordAuto;
    private Button mCreateAccountButton;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonMale;
    private RadioButton mRadioButtonFemale;


    FirebaseAuth firebaseAuth;
    private FirebaseStorage mFirebaseStoarge;
    private StorageReference mUserPhotoStoargeReference;
    ProgressBar progressBar;
    ImageView selectImage;
    ImageView choosenImage;
    Uri selectedImageUri;

    private static final int RC_PHOTO_PICKER =  2;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            choosenImage.setImageURI(selectedImageUri);

            // Get a reference to store file at chat_photos/<FILENAME>

        }
    }


    public SignupDoctor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_signup_doctor, container, false);
        //initialize Firebase
        firebaseAuth= FirebaseAuth.getInstance();
        mFirebaseStoarge= FirebaseStorage.getInstance();
        mUserPhotoStoargeReference=mFirebaseStoarge.getReference().child("user_photos");
        //Bind UI references
        selectImage=(ImageView)rootView.findViewById(R.id.select_image);
        choosenImage=(ImageView)rootView.findViewById(R.id.choosen_image);
        mUserNameView = (AutoCompleteTextView)rootView.findViewById(R.id.doctor_name);
        mUserNmaeAuto = (TextInputLayout)rootView.findViewById(R.id.doctor_name_auto);
        progressBar=(ProgressBar)rootView.findViewById(R.id.progress_doctor);
        progressBar.setVisibility(View.GONE);
        mCreateAccountButton = (Button)rootView.findViewById(R.id.create_account_doctor_button);
        mEmailView = (AutoCompleteTextView)rootView.findViewById(R.id.email_doctor);
        mPasswordView = (EditText)rootView.findViewById(R.id.password_doctor);
        mEmailAuto = (TextInputLayout) rootView.findViewById(R.id.email_auto_doctor);
        mPasswordAuto =(TextInputLayout)rootView.findViewById(R.id.password_auto_doctor);
        mNationalIDAuto=(TextInputLayout)rootView.findViewById(R.id.nationalid_auto_doctor);
        mNationalIDView=(AutoCompleteTextView) rootView.findViewById(R.id.nationalid_doctor);
        mAgeView=(AutoCompleteTextView)rootView.findViewById(R.id.age_doctor);
        mAgeAuto=(TextInputLayout)rootView.findViewById(R.id.age_auto_doctor);
        mRadioGroup=(RadioGroup)rootView.findViewById(R.id.radio_doctor);
        mRadioButtonMale=(RadioButton)rootView.findViewById(R.id.male);
        mRadioButtonFemale=(RadioButton)rootView.findViewById(R.id.female);

        selectedImageUri=null;

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordAuto.setError(null);
                mAgeAuto.setError(null);
                mEmailAuto.setError(null);
                mNationalIDAuto.setError(null);
                mUserNmaeAuto.setError(null);
                final String password = mPasswordView.getText().toString();
                String age = mAgeView.getText().toString();
                final String email = mEmailView.getText().toString();
                final String userName = mUserNameView.getText().toString();
                final String gender;
                String nationalID=mNationalIDView.getText().toString();
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
                if(nationalID.length()<14){
                    mNationalIDAuto.setError("Invalid National ID");
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
                if(selectedImageUri==null){
                    Toast.makeText(getContext(),"Please Choose Photo",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                StorageReference photoRef = mUserPhotoStoargeReference.child(selectedImageUri.getLastPathSegment());

                // Upload file to Firebase Storage
                photoRef.putFile(selectedImageUri)
                        .addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // When the image has successfully uploaded, we get its download URL
                                final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                // Set the download URL to the message box, so that the user can send it to the database
                                // FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadUrl.toString());
                                //  mMessageDatabaseReference.push().setValue(friendlyMessage);
                                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if(task.isSuccessful()){
                                            Doctor doctor=new Doctor(Integer.parseInt(mAgeView.getText().toString()),gender,userName,2,mNationalIDView.getText().toString(),downloadUrl.toString());
                                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(doctor);
                                            Intent intent =new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                            //  getActivity().onBackPressed();

                                        }else{
                                            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                        });











            }
        });
        return rootView;

    }

}
