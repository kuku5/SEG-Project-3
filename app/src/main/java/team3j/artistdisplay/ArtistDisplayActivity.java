package team3j.artistdisplay;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;

public class ArtistDisplayActivity extends ActionBarActivity {

    private TextView textView;
    private ImageView shareButton;
    private TextView artDescription;
    private TextView artist;
    private ImageView stikArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_display);

        Intent myIntent = getIntent(); // gets the previously created intent
        //textView = (TextView) findViewById(R.id.test123);

        int indexOfArtWork = myIntent.getIntExtra("indexOfArtWork", 0);

        final String artistName = GalleryData.GetArtistsData(this).get(indexOfArtWork);
        if (savedInstanceState != null) {

            textView.setText("Hello World this artist is   " + artistName);
            // textView.setText("Hello World this artist is   " +    artistName );
        }

        shareButton = (ImageView) findViewById(R.id.shareIcon);
        artDescription = (TextView) findViewById(R.id.moodySamTom);
        artist = (TextView) findViewById(R.id.position);
        stikArt = (ImageView) findViewById(R.id.stikImage);

        stikArt.setImageResource(R.drawable.stik2012);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String artDesc = "Art Description: " + artDescription.getText().toString();
                String artistName = "Artist: " + artist.getText().toString() + "\n";

                Intent artInfoIntent = new Intent(Intent.ACTION_SEND);
                artInfoIntent.putExtra(Intent.EXTRA_TEXT, artistName + artDesc);
                artInfoIntent.setType("text/plain");
                startActivity(artInfoIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_display, menu);
        return true;
    }

    View.OnClickListener shareClicking = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Bitmap bitmap;
            OutputStream output;

            // Retrieve the image from the res folder
            bitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher);

            // Find the SD Card path
            File filepath = Environment.getExternalStorageDirectory();

            // Create a new folder AndroidBegin in SD Card
            File dir = new File(filepath.getAbsolutePath() + "/Share Image Tutorial/");
            dir.mkdirs();

            // Create a name for the saved image
            File file = new File(dir, "sample_wallpaper.png");

            try {

                // Share Intent
                Intent share = new Intent(Intent.ACTION_SEND);

                // Type of file to share
                share.setType("image/jpeg");

                output = new FileOutputStream(file);

                // Compress into png format image from 0% - 100%
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();

                // Locate the image to Share
                Uri uri = Uri.fromFile(file);

                // Pass the image into an Intent
                share.putExtra(Intent.EXTRA_STREAM, uri);

                // Show the social share chooser list
                startActivity(Intent.createChooser(share, "Share Image Tutorial"));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


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
