package com.mzs.vibrokit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavHostFragment navHostFragment=(NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host);
        NavController navController=navHostFragment.getNavController();
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        appBarConfiguration=new AppBarConfiguration
                .Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController= Navigation.findNavController(this, R.id.nav_host);
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                ||super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret=super.onCreateOptionsMenu(menu);
        try{
            getMenuInflater().inflate(R.menu.menu_main,menu);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        NavHostFragment host=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavController navController=host.getNavController();
//        old method
//        if(item.getItemId()==R.id.main_dest){
//            navController.navigate(R.id.main_dest);
//        }
//        return super.onOptionsItemSelected(item);
//        new method
         return   NavigationUI.onNavDestinationSelected(item, navController)
                    ||super.onOptionsItemSelected(item);
    }

}