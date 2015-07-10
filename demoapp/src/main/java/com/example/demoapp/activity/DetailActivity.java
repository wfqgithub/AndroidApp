package com.example.demoapp.activity;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.demoapp.R;
import com.example.demoapp.model.Gril;
import com.facebook.drawee.view.SimpleDraweeView;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    private SimpleDraweeView simpleDraweeView1,simpleDraweeView2;
    private CardView cardView1,cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }


    private void initView(){
        Gril gril= (Gril) getIntent().getSerializableExtra("Gril");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(gril.getTitle());

        simpleDraweeView1 = (SimpleDraweeView) findViewById(R.id.first_sdview);
        simpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.sec_sdview);
        simpleDraweeView1.setImageURI(Uri.parse(gril.getUrl()));
        simpleDraweeView2.setImageURI(Uri.parse(gril.getUrl()));

        loadBackdrop();

        cardView1 = (CardView) findViewById(R.id.first_cardview);
        cardView2 = (CardView) findViewById(R.id.sec_cardview);
        cardView1.setCardElevation(500);
        cardView2.setCardElevation(200);

    }


    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        imageView.setImageResource(R.mipmap.girl1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
