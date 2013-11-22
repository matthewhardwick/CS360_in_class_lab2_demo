package io.hardwick.lab2;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by matthewhardwick on 11/20/13.
 */
public class DetailsFragment extends Fragment {
    private String item;
    private Bitmap itemBitmap;
    private ImageView itemImageView;
    private TextView itemTextView;

    public DetailsFragment(String item) {
        this.item = item;
    }

    public void setImageView(Bitmap imageBitmap) {
        this.itemBitmap = imageBitmap;
        itemImageView.setImageBitmap(imageBitmap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_view_fragment_layout, container, false);

        itemTextView = (TextView) view.findViewById(R.id.detailsTextView);
        itemTextView.setText(item);
        itemImageView = (ImageView) view.findViewById(R.id.detailsImageView);

        if (itemBitmap != null)
            itemImageView.setImageBitmap(itemBitmap);


        return view;
    }
}