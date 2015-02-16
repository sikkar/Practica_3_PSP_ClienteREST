package com.izv.angel.appinstituto;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class Aniadir extends Activity  {

    private FragmentoComplementaria fc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_aniadir);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Complementarias");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Extraescolares");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        FragmentoComplementaria fcomp = (FragmentoComplementaria) getFragmentManager().findFragmentById(R.id.fragmento_complementaria);
        FragmentoComplementaria fextra = (FragmentoComplementaria) getFragmentManager().findFragmentById(R.id.fragmento_extraescolar);
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aniadir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {
                NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
