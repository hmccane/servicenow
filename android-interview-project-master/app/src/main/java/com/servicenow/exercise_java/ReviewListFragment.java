/**
 * ReviewListFragment - Fragment with a simple RecyclerView that holds the review list
 * A OnClickListener is installed on each item view of the recyclerview.  When a user
 * clicks the item, the detailed review page is displayed.   This is achieved by loading DetailFragment
 * and pass the item information via bundled argument.
 *
 * @author Henry Song
 */
package com.servicenow.exercise_java;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.coffee.Review;
import com.servicenow.coffee.CoffeeShopReviews;
import com.servicenow.exercise.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ReviewListFragment extends Fragment {
    static final Review[] coffeeShopReviews = CoffeeShopReviews.INSTANCE.getList();
    // RecyclverView adapter, accessed by AsyncReview background task
    ReviewListAdapter adapter;
    // data used by adapter
    static Review[] data = null;
    // flag indicates whether review part of the data has been pulled from the server
    static boolean populated = false;

    public ReviewListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (data == null) {
            data = coffeeShopReviews;
            // initially the review part of data is missing
            for (int i = 0; i < data.length; i++) {
                data[i].setReview("");
            }
        }
        // create the recyclerview, initially when shown on the screen, the review part of the data
        // is empty
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReviewListAdapter();
        rv.setAdapter(adapter);

        // only pull once
        if (populated == false)
            new AsyncReview().execute("https://jsonblob.com/api/jsonBlob/c1a89a37-371e-11ea-a549-6f3544633231");
        return rv;
    }

    private class AsyncReview extends AsyncTask<String, Void, Map<String, String>> {
        final int CONNECTION_TIMEOUT = 2000;
        final int READ_TIMEOUT = 1000;

        @Override
        protected Map<String, String> doInBackground(String... params) {
            URL url;
            HttpURLConnection conn;
            Map<String, String> res = new HashMap<>();
            try {
                url = new URL(params[0]);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return res;
            }
            try {
                // Setup https connection to get data from url
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return res;
            }

            try {
                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    // parse json
                    JSONArray array = new JSONArray(sb.toString());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        String name = o.getString("name");
                        String review = o.getString("review");
                        res.put(name, review);
                    }
                    // post data to onPostExecute
                    return res;
                } else {
                    return res;
                }

            } catch (IOException e1) {
                e1.printStackTrace();
                return res;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return res;
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (result.isEmpty() == false)
                adapter.populateData(result);
        }
    }

    /**
     * A Simple Adapter for the RecyclerView
     */
    public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListViewHolder> {
        //private Review[] dataSource;
        public ReviewListAdapter(){ }

        public void populateData(Map<String, String> review) {
            for (int i = 0; i < data.length; i++) {
                String name = data[i].getName();
                if (review.containsKey(name)) {
                    data[i].setReview(review.get(name));
                }
            }
            populated = true;
            notifyDataSetChanged();
        }

        @Override
        public ReviewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
            ReviewListViewHolder viewHolder = new ReviewListViewHolder(row);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ReviewListViewHolder holder, int position) {
            holder.shop = data[position].getName();
            holder.location = data[position].getLocation();
            holder.rating = Integer.toString(data[position].getRating());
            holder.review = data[position].getReview();

            holder.shopName.setText(holder.shop);
            holder.reviewText.setText(holder.review);
            holder.reviewImage.setImageResource(Review.getIconResourceFromName(holder.shop));

            // install OnClickListener
            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("shop", holder.shop);
                    bundle.putString("rating", holder.rating);
                    bundle.putString("location", holder.location);
                    bundle.putString("review", holder.review);
                    // FIXME: I could provide a singleton utility class that holds instance of
                    // DetailFragment such that it will not be created every time.
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(bundle);
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.activity_frame, detailFragment, "Detail Fragment")
                            .addToBackStack(null)
                            .commit();

                }
            });
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }

    /**
     * A Simple ViewHolder for the RecyclerView 
     */
    public static class ReviewListViewHolder extends RecyclerView.ViewHolder{
        // visible views in the item
        public TextView reviewText;
        public TextView shopName;
        public ImageView reviewImage;
        public View row;

        // information needs to pass to DetailFragment
        public String review;
        public String shop;
        public String rating;
        public String location;

        public ReviewListViewHolder(View itemView) {
            super(itemView);
            row = itemView;
            shopName = (TextView) itemView.findViewById(R.id.text1);
            reviewText = (TextView) itemView.findViewById(R.id.text2);
            reviewImage = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}