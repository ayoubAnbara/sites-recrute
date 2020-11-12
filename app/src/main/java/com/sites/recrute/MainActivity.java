package com.sites.recrute;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sites.recrute.adapters.MyAdapter;
import com.sites.recrute.model.Site;
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
                new Site(1, "careers-page.com", "https://www.careers-page.com/diaaland#openings"),
                new Site(2, "rekrute.com", "https://www.rekrute.com/offres.html?st=d&keywordNew=1&keyword=&sectorId%5B%5D=24&filterLogo=&filterLogo="),
                new Site(3, "optioncarriere.ma", "https://www.optioncarriere.ma/emploi-developpement-informatique.html"),
                new Site(4, "ma.indeed.com", "https://ma.indeed.com/emplois?q=d%C3%A9veloppeur&l=morocco"),
                new Site(5, "wetech.ma", "https://www.wetech.ma/offres-emploi/"),
                new Site(6, "upforjob.com", "https://www.upforjob.com/posts?sect=131&ctry=212"),
                new Site(7, "sofrecom.com", "https://www.sofrecom.com/fr/jobs?utf8=%E2%9C%93&CTNT%5B%5D=AFRICA&CATG%5B%5D=TIS&button="),
                new Site(8, "jobs.atos.net", "https://jobs.atos.net/?locale=en_US&logout=true"),
                new Site(9, "orange.jobs", "https://orange.jobs/site/ma-orangemaroc/index.htm"),
                new Site(10, "sqli-carrieres.com", "https://www.sqli-carrieres.com/"),
                new Site(11, "novancy.com", "https://www.novancy.com/jobs"),
                new Site(12, "jora.com", "https://ma.jora.com/D%C3%A9veloppeur-Java-emplois"),
                new Site(13, "cgi.com", "https://www.cgi.com/fr/carrieres-recherche?country_id=388&job_type_id=&category_id=34"),
                new Site(14, "jobrapido.com", "https://ma.jobrapido.com/?w=d%c3%a9veloppeur&l=maroc&r=auto&shm=all"),
                new Site(15, "neuvoo.co.ma", "https://neuvoo.co.ma/emplois/"),
                new Site(16, "emploi.ma", "https://www.emploi.ma/recherche-jobs-maroc/?f%5B0%5D=im_field_offre_metiers%3A31"),
                new Site(17, "charikates.ma", "https://charikates.ma/")
                //  new Site(17, "google", "https://www.google.com/search?sxsrf=ALeKk02GMLWQGfgr1xmzlpOHtDMcrRqy3A:1604080549244&ei=pVOcX-nMDtCU8gLywpXIAw&q=developpeur+&oq=sites+recrute&gs_lcp=CgZwc3ktYWIQAzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIICAAQChAeEBMyCAgAEAUQHhATMggIABAFEB4QEzIICAAQBRAeEBM6BAgAEEdQjSBYjSBgnCRoAHACeACAAbQBiAG0AZIBAzAuMZgBAKABAaoBB2d3cy13aXrIAQPAAQE&sclient=psy-ab&uact=5&ibp=htl;jobs&sa=X&ved=2ahUKEwjNjLLd8dzsAhXUlFwKHX4mBmAQiYsCKAJ6BAgMEBA&sxsrf=ALeKk03XyKFTrq4I8tBpn6iAjbvL9j1BSQ:1604080555041#fpstate=tldetail&htivrt=jobs&htidocid=NDvr2E6_I-mA7Yp_AAAAAA%3D%3D")

        );

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // run your one time code

            Map<String, Boolean> preferenceMap = new HashMap<>();
            for (Site s : sites) {
                preferenceMap.put(String.valueOf(s.getId()), s.isPreferred());
            }
            PowerPreference.getDefaultFile().putObject("preferenceMap", preferenceMap);

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


    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.rateUs:
                rateUs();
                return true;
            case R.id.code_source:
                final String url_github = "https://github.com/ayoub96anbara/sites-recrute";
                open_url(url_github);
                return true;

            case R.id.privacy_policy:
                final String url_privacy_policy = getString(R.string.url_pravicy);
                open_url(url_privacy_policy);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void rateUs() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void open_url(String url) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
