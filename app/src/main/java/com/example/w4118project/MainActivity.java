package com.example.w4118project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.w4118project.adapter.PageAdapter;
import com.example.w4118project.fragment.HomeFragment;
import com.example.w4118project.fragment.PerfilFragment;
import com.example.w4118project.restAPI.ConstantesRestAPI;
import com.google.android.material.tabs.TabLayout;

import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);

        setupViewPager();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ConstantesRestAPI.USER_ID = leerCuenta();
    }

    private String leerCuenta() {
        String s = "";
        try {
            FileInputStream fis = openFileInput(Config.NOMBRE_ARCHIVO);
            int c;

            while ((c = fis.read()) != -1) {
                s += Character.toString((char) c);
            }
            //Toast.makeText(this, "Cuenta guardada: " + s, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Log.e("TAG", "[Config.leerCuenta()] ERROR: " + e.getMessage());
            Toast.makeText(this, "ERROR Leyendo Cuenta", Toast.LENGTH_SHORT).show();
        }
        return s;
    }

    private void setupViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, agregarFragments()));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.i_favoritos:
                startActivity(new Intent(this, Favoritos.class));
                break;
            case R.id.i_contacto:
                startActivity(new Intent(this, Contacto.class));
                break;
            case R.id.i_about:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.i_config:
                startActivity(new Intent(this, Config.class));
                break;
        }
        return true;
    }
}