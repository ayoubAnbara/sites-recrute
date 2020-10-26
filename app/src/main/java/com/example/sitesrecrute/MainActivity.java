package com.example.sitesrecrute;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sitesrecrute.adapters.MyAdapter;
import com.example.sitesrecrute.model.Site;
import com.preference.PowerPreference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PowerPreference.init(this);

        final List<Site> sites = Arrays.asList(
                new Site(1,"careers-page.com", "https://www.careers-page.com/diaaland#openings"),
                new Site(2,"rekrute.com","https://www.rekrute.com/offres.html?st=d&keywordNew=1&keyword=&sectorId%5B%5D=24&filterLogo=&filterLogo="),
                new Site(3,"optioncarriere.ma","https://www.optioncarriere.ma/emploi-developpement-informatique.html"),
                new Site(4,"ma.indeed.com","https://ma.indeed.com/?r=us"),
                new Site(5,"wetech.ma","https://www.wetech.ma/"),
                new Site(6,"upforjob.com","https://www.upforjob.com/home"),
                new Site(7,"sofrecom.com","https://www.sofrecom.com/fr/jobs?utf8=%E2%9C%93&CTNT%5B%5D=AFRICA&CATG%5B%5D=TIS&button="),
                new Site(8,"jobs.atos.net","https://jobs.atos.net/?locale=en_US&logout=true"),
                new Site(9,"orange.jobs","https://orange.jobs/site/ma-orangemaroc/index.htm"),
                new Site(10,"sqli-carrieres.com","https://www.sqli-carrieres.com/"),
                new Site(11,"charikates.ma","https://charikates.ma/"),
            new Site(12,"https://ma.jora.com/D%C3%A9veloppeur-Java-emplois")
        );

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
       if(!prefs.getBoolean("firstTime", false)) {
            // run your one time code

             Map<String,Boolean> preferenceMap=new HashMap<>();
            for (Site s:sites) {
                preferenceMap.put(String.valueOf(s.getId()),s.isPreferred());
            }
            PowerPreference.getDefaultFile().putObject("preferenceMap",preferenceMap);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        }


        RecyclerView recyclerView = findViewById(R.id.recycleView);


        MyAdapter myAdapter = new MyAdapter(getBaseContext(), sites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);


    }

    public void onClick(View view) {
        final String SITE;
        switch (view.getId()) {
            case R.id.diaa:
                SITE = "https://www.careers-page.com/diaaland#openings";
                break;
            case R.id.rekrute:
                SITE = "https://www.rekrute.com/offres.html?st=d&keywordNew=1&keyword=&sectorId%5B%5D=24&filterLogo=&filterLogo=";
                break;
            case R.id.optioncarriere:
                SITE = "https://www.optioncarriere.ma/emploi-developpement-informatique.html";
                break;
            case R.id.indeed:
                SITE = "https://ma.indeed.com/?r=us";
                break;

            case R.id.wetech:
                SITE = "https://www.wetech.ma/";
                break;

            case R.id.upforjob:
                SITE = "https://www.upforjob.com/home";
                break;

            case R.id.sofrecom:
                SITE = "https://www.sofrecom.com/fr/jobs?utf8=%E2%9C%93&CTNT%5B%5D=AFRICA&CATG%5B%5D=TIS&button=";
                break;
            case R.id.atos:
                SITE = "https://jobs.atos.net/?locale=en_US&logout=true";
                break;
            case R.id.orange:
                SITE = "https://orange.jobs/site/ma-orangemaroc/index.htm";
                break;
            case R.id.sqli:
                SITE = "https://www.sqli-carrieres.com/";
                break;
            case R.id.charikates:
                SITE = "https://charikates.ma/";
                break;
            default:
                SITE = "";

        }
        Intent intent = new Intent(this, MyWebView.class);
        intent.putExtra("site", SITE);
        startActivity(intent);
    }
}
