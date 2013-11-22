package io.hardwick.lab2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.*;

public class MainActivity extends Activity implements ListViewFragment.OnDetailView {
    List<String> items;
    ListViewFragment listViewFragment;
    DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<String>();
        for (int i = 0; i < 25; ++i) {
            items.add("List Item " + i);
        }

        listViewFragment = new ListViewFragment(items);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.listViewContainer, listViewFragment)
                    .add(R.id.detailsContainer, new DetailsFragment("Welcome to the Fragment!"))
                    .commit();
        }
    }


    // http://watts.cs.sonoma.edu/tia.jpg
    @Override
    public void onDetailViewUpdate(int position) {
        detailsFragment = new DetailsFragment(items.get(position));
        getFragmentManager().beginTransaction()
                .replace(R.id.detailsContainer, detailsFragment)
                .addToBackStack("DetailBack")
                .commit();

        AsyncHttpClient client = new AsyncHttpClient();
        String[] allowedContentTypes = new String[] { "image/jpeg" };
        client.get("http://watts.cs.sonoma.edu/tia.jpg", new BinaryHttpResponseHandler(allowedContentTypes) {
            @Override
            public void onSuccess(byte[] fileData) {
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                if (detailsFragment != null)
                    detailsFragment.setImageView(imageBitmap);
            }
        });

    }
}
