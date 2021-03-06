package com.deerslab.mathbomb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class CategoryChooserActivity extends Activity {

    private SharedPreferences preferences;
    private Resources resources;
    private ListView listView;
    String TAG = this.getClass().getSimpleName();
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_chooser);

        resources = getResources();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        CategoryChooserItem chooserItems[] = new CategoryChooserItem[]{
                new CategoryChooserItem(resources.getString(R.string.PLUS1), preferences.getInt("progressPLUS1", 0), preferences.getBoolean("accessiblePLUS1", true)),
                new CategoryChooserItem(resources.getString(R.string.MINUS1), preferences.getInt("progressMINUS1", 0), preferences.getBoolean("accessibleMINUS1", true)),
                new CategoryChooserItem(resources.getString(R.string.MISC1), preferences.getInt("progressMISC1", 0), preferences.getBoolean("accessibleMISC1", true)),
                new CategoryChooserItem(resources.getString(R.string.MULT), preferences.getInt("progressMULT", 0), preferences.getBoolean("accessibleMULT", true)),
                new CategoryChooserItem(resources.getString(R.string.PLUS2), preferences.getInt("progressPLUS2", 0), preferences.getBoolean("accessiblePLUS2", true)),
                new CategoryChooserItem(resources.getString(R.string.DIVISION), preferences.getInt("progressDIVISION", 0), preferences.getBoolean("accessibleDIVISION", true)),
                new CategoryChooserItem(resources.getString(R.string.MINUS2), preferences.getInt("progressMINUS2", 0), preferences.getBoolean("accessibleMINUS2", true))
        };

        CategoryChooserAdapter adapter = new CategoryChooserAdapter(this, R.layout.category_item, chooserItems);
        listView = (ListView) findViewById(R.id.lvCategories);

        View header = (View)getLayoutInflater().inflate(R.layout.category_header, null);

        listView.addHeaderView(header, null, false);
        listView.setAdapter(adapter);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        if (preferences.getBoolean("accessiblePLUS1", true)){
                            Database.currentCategory = CategoriesEnum.PLUS1;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 2:
                        if (preferences.getBoolean("accessibleMINUS1", true)){
                            Database.currentCategory = CategoriesEnum.MINUS1;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 3:
                        if (preferences.getBoolean("accessibleMISC1", true)){
                            Database.currentCategory = CategoriesEnum.MISC1;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 4:
                        if (preferences.getBoolean("accessibleMULT", true)){
                            Database.currentCategory = CategoriesEnum.MULT;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 5:
                        if (preferences.getBoolean("accessiblePLUS2", true)){
                            Database.currentCategory = CategoriesEnum.PLUS2;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 6:
                        if (preferences.getBoolean("accessibleDIVISION", true)){
                            Database.currentCategory = CategoriesEnum.DIVISION;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                    case 7:
                        if (preferences.getBoolean("accessibleMINUS2", true)){
                            Database.currentCategory = CategoriesEnum.MINUS2;
                            startActivity(new Intent(CategoryChooserActivity.this, LevelChooserActivity.class));
                            finish();
                        }
                        break;
                }
            }
        });

        try {
            mTracker = AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
            mTracker.setScreenName(TAG);
            mTracker.send(new HitBuilders.EventBuilder().setAction("start").build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(CategoryChooserActivity.this, StartActivity.class));
        finish();
    }
}
