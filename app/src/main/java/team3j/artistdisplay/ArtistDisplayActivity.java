package team3j.artistdisplay;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;

public class ArtistDisplayActivity extends ActionBarActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_display);
        Intent myIntent = getIntent(); // gets the previously created intent
        textView = (TextView) findViewById(R.id.test123);

        int indexOfArtWork = myIntent.getIntExtra("indexOfArtWork", 0);

        String artistName= GalleryData.GetArtistsData(this).get(indexOfArtWork);

        if (savedInstanceState != null) {


            textView.setText("Hello World this artist is   " +    artistName );

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_display, menu);
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
