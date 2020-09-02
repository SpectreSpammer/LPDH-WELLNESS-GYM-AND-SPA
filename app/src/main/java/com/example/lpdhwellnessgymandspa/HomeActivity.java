package com.example.lpdhwellnessgymandspa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private ScheduleFragment scheduleFragment;
    private DashboardFragment dashboardFragment;
    private AccountFragment accountFragment;
    private NotificationsFragment notificationsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        dashboardFragment = new DashboardFragment();
        accountFragment = new AccountFragment();
        notificationsFragment = new NotificationsFragment();

        setFragment(homeFragment);
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;


                    case R.id.nav_schedule:
                        setFragment(scheduleFragment);
                        return true;


                    case R.id.nav_dashboard:
                        setFragment(dashboardFragment);
                        return true;


                    case R.id.nav_account:
                        setFragment(accountFragment);
                        return true;



                    case R.id.nav_notifications:
                        setFragment(notificationsFragment);
                        return true;

                    default:
                        return false;
                }
            }


        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }




    }


