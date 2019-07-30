package com.example.android.secrethands;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.secrethands.fragments.About;
import com.example.android.secrethands.fragments.ChooseDoctor;
import com.example.android.secrethands.fragments.FellingsFragment;
import com.example.android.secrethands.fragments.HomePatient;
import com.example.android.secrethands.fragments.ReadaBook;
import com.example.android.secrethands.fragments.Schedule;
import com.example.android.secrethands.fragments.SchedulePager;
import com.example.android.secrethands.fragments.Session;
import com.example.android.secrethands.fragments.getoverit;
import com.example.android.secrethands.fragments.routines;
import com.example.android.secrethands.fragments.share_day;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements HomePatient.OnImageClickListener {


    private HomePatient homePatient;
    private SchedulePager schedule;
    private About about;
    private ChooseDoctor chooseDoctor;
    private Session session;
    private getoverit getoverit;
    private ReadaBook readaBook;
    private routines routines;
    private FellingsFragment fellingsFragment;
    private share_day shareDay;

    private
    long type;
    ProgressBar progressBar;

    @Override
    public void onBackPressed() {
      super.onBackPressed();
    }

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
        fellingsFragment=new FellingsFragment();
        fellingsFragment.initCallback(new FellingsFragment.OnImageClickListener() {
            @Override
            public void onImageSelected() {
                loadFragment(getoverit);
            }
        });
        homePatient=new HomePatient();
        schedule=new SchedulePager();
        session =new Session();
        chooseDoctor=new ChooseDoctor();
        getoverit=new getoverit();
        shareDay=new share_day();
        getoverit.initCallback(new getoverit.OnImageClickListener() {
            @Override
            public void onImageSelected(int i) {
                if(i==3){
                    loadFragment(routines);
                }
            }
        });
        routines=new routines();
        routines.initCallback(new routines.OnImageClickListener() {
            @Override
            public void onImageSelected(int i) {
                if(i==2){
                    loadFragment(readaBook);

                }
                else if(i==3){
                    loadFragment(shareDay);

                }



            }
        });
        readaBook=new ReadaBook();



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
                                loadFragment(homePatient);




                            }
                            else{
                                navigation.getMenu().removeItem(R.id.navigation_home);


                                navigation.getMenu().removeItem(R.id.navigation_schedule);

                                about=new About(mFirebaseAuth.getCurrentUser().getUid());
                                loadFragment(about);



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


    @Override
    public void onImageSelected(int i) {

        if(i==0){
            loadFragment(fellingsFragment);
        }
        else if(i==1){
            loadFragment(chooseDoctor);

        }


    }
}
