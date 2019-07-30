package com.example.android.secrethands;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.secrethands.fragments.About;
import com.example.android.secrethands.fragments.ChooseDoctor;
import com.example.android.secrethands.fragments.HomePatient;
import com.example.android.secrethands.fragments.Schedule;
import com.example.android.secrethands.fragments.SchedulePager;
import com.example.android.secrethands.fragments.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private HomePatient homePatient;
    private SchedulePager schedule;
    private About about;
    private ChooseDoctor chooseDoctor;
    private Session session;

    long type;
    ProgressBar progressBar;

    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserTypeDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(homePatient);

                    return true;

                case R.id.navigation_schedule:
                  loadFragment(schedule);
                    return true;
                case R.id.about:
                    loadFragment(about);
                    return true;
                case R.id.sessions:
                    loadFragment(session);
                    return true;
                case R.id.navigation_profile:
                    return true;
            }
            return false;
        }
    };
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intialize fragments
        homePatient=new HomePatient();
        schedule=new SchedulePager();
        session =new Session();
        chooseDoctor=new ChooseDoctor();

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressBar=(ProgressBar) findViewById(R.id.progress_par);
        progressBar.setVisibility(View.GONE);
      //  navigation.getMenu().removeItem(R.id.about);
        // Decide Doctor or Patient
        //firebase initialize
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseAuth= FirebaseAuth.getInstance();

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){




                    mUserTypeDatabaseReference=mFirebaseDatabase.getReference().child("Users").child(firebaseUser.getUid()).child("type");
                    mUserTypeDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Toast.makeText(getApplicationContext(),dataSnapshot.getValue(long.class).toString(),Toast.LENGTH_SHORT).show();
                            type=dataSnapshot.getValue(long.class);
                            progressBar.setVisibility(View.GONE);
                            if(type==1){
                                navigation.getMenu().removeItem(R.id.about);
                                navigation.getMenu().removeItem(R.id.sessions);




                            }
                            else{
                                navigation.getMenu().removeItem(R.id.navigation_home);
                                navigation.getMenu().removeItem(R.id.navigation_profile);
                                navigation.getMenu().removeItem(R.id.navigation_schedule);

                                about=new About(mFirebaseAuth.getCurrentUser().getUid());



                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    Toast.makeText(MainActivity.this, "Welcome To Secret Hands", Toast.LENGTH_SHORT).show();

                }
                else{

                }

            }
        };

    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss();

            fragmentManager.executePendingTransactions();
            return true;
        } else return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener!=null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
    public void loadDoctorList(){
        loadFragment(chooseDoctor);


    }

}
