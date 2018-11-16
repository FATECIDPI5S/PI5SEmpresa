package br.com.icomidaempresa;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        abrirDashBoard();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        if (id == R.id.action_settings) {
            return true;
        }

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
            mFragment = new MenuPrecoFragment();
            setTitle(R.string.title_fragment_menu_preco);

        } else if (id == R.id.nav_configuracoes) {
            mFragment = new ConfiguracoesFragment();
            setTitle(R.string.title_fragment_configuracoes);

        } else if (id == R.id.nav_sobre) {
            mFragment = new SobreFragment();
            setTitle(R.string.title_fragment_sobre);
        }

        if(mFragment != null){
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.mainLayout, mFragment);
            mFragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirDashBoard(){
        Fragment mFragment = new DashboardFragment();
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.mainLayout, mFragment);
        mFragmentTransaction.commit();
        setTitle(R.string.title_fragment_dashboard);
    }
}
