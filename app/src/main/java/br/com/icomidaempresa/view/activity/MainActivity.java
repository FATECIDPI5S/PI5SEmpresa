package br.com.icomidaempresa.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Administrador;
import br.com.icomidaempresa.view.fragment.AmbienteMesaFragment;
import br.com.icomidaempresa.view.fragment.ColaboradoresFragment;
import br.com.icomidaempresa.view.fragment.ConfiguracoesFragment;
import br.com.icomidaempresa.view.fragment.DashboardFragment;
import br.com.icomidaempresa.view.fragment.EmpresaFragment;
import br.com.icomidaempresa.view.fragment.MenuFragment;
import br.com.icomidaempresa.view.fragment.ProdutosFragment;
import br.com.icomidaempresa.view.fragment.SobreFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private FirebaseDatabase mFirebaseDatabase;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        AdicionarAdministradorListener();
        abrirDashBoard();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment mFragment = null;

        if (id == R.id.nav_dashboard) {
            mFragment = new DashboardFragment();
            setTitle(R.string.title_fragment_dashboard);

        } else if (id == R.id.nav_empresa) {
            mFragment = new EmpresaFragment();
            setTitle(R.string.title_fragment_empresa);

        } else if (id == R.id.nav_colaboradores) {
            mFragment = new ColaboradoresFragment();
            setTitle(R.string.title_fragment_colaboradores);

        } else if (id == R.id.nav_produtos) {
            mFragment = new ProdutosFragment();
            setTitle(R.string.title_fragment_produtos);

        } else if (id == R.id.nav_ambiente_mesa) {
            mFragment = new AmbienteMesaFragment();
            setTitle(R.string.title_fragment_ambiente_mesa);

        } else if (id == R.id.nav_menu_preco) {
            mFragment = new MenuFragment();
            setTitle(R.string.title_fragment_menu);

        } else if (id == R.id.nav_configuracoes) {
            mFragment = new ConfiguracoesFragment();
            setTitle(R.string.title_fragment_configuracoes);

        } else if (id == R.id.nav_sobre) {
            mFragment = new SobreFragment();
            setTitle(R.string.title_fragment_sobre);
        }

        if (mFragment != null) {
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.mainLayout, mFragment);
            mFragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirDashBoard() {
        Fragment mFragment = new DashboardFragment();
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.mainLayout, mFragment);
        mFragmentTransaction.commit();
        setTitle(R.string.title_fragment_dashboard);
    }

    public void AdicionarAdministradorListener() {
        DatabaseReference mUsuarioDatabaseReference = mFirebaseDatabase.getReference().child("administrador");
        mUsuarioDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Administrador admin = dataSnapshot.getValue(Administrador.class);
                if (admin.getEmail().equals(user.getEmail())) {
                    CarregarDadosLogin(admin);
                    salvarDadosAdmin(user.getEmail(), dataSnapshot.getKey());
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Administrador admin = dataSnapshot.getValue(Administrador.class);
                if (admin.getEmail().equals(user.getEmail())) {
                    CarregarDadosLogin(admin);
                    salvarDadosAdmin(user.getEmail(), dataSnapshot.getKey());
                }
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void CarregarDadosLogin(Administrador admin) {
        try {
            TextView tvNavHeaderTitle = navigationView.findViewById(R.id.tvNavHeaderTitle);
            tvNavHeaderTitle.setText(admin.getNome());
            TextView tvNavHeaderSub = navigationView.findViewById(R.id.tvNavHeaderSubtitle);
            tvNavHeaderSub.setText(admin.getEmail());
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void salvarDadosAdmin(String email, String adminKey) {
        SharedPreferences preferences = getSharedPreferences("admin_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("adminKey", adminKey);
        editor.apply();
        editor.commit();
    }
}