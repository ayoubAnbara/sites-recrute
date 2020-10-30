package com.sites.recrute.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sites.recrute.MyWebView;
import com.sites.recrute.R;
import com.sites.recrute.model.Site;
import com.preference.PowerPreference;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;

    private List<Site> siteList;
    // Constructor
    private Map<String, Boolean> preferenceMap;

    public MyAdapter(Context mContext, List<Site> siteList) {
        this.mContext = mContext;
        this.siteList = siteList;
        preferenceMap = PowerPreference.getDefaultFile().getObject("preferenceMap", Object.class);


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());


        View itemView = inflater.inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder MyViewHolder, final int position) {

        MyViewHolder.textView.setText(siteList.get(position).getLabel());
        MyViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "click to "+exercicesList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MyWebView.class);
                intent.putExtra("site", siteList.get(position).getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);


            }
        });
        ImageView imageView = MyViewHolder.img_start;
        boolean isPrefered = preferenceMap.get(String.valueOf(position + 1));
        if (isPrefered) {
            imageView.setImageResource(R.drawable.ic_baseline_star_24);
            imageView.setTag(R.drawable.ic_baseline_star_24 + "");
        } else {
            imageView.setImageResource(R.drawable.ic_twotone_star_24);
            imageView.setTag(R.drawable.ic_twotone_star_24 + "");

        }

        MyViewHolder.img_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                String resource = (String) v.getTag();


              //  System.out.println(resource.equals(String.valueOf(R.drawable.ic_baseline_star_24)));
                if (resource.equals(String.valueOf(R.drawable.ic_baseline_star_24))) {
                    imageView.setImageResource(R.drawable.ic_twotone_star_24);
                    imageView.setTag(R.drawable.ic_twotone_star_24 + "");
                    preferenceMap.put(String.valueOf(position + 1),false);

                } else {
                    imageView.setImageResource(R.drawable.ic_baseline_star_24);
                    imageView.setTag(R.drawable.ic_baseline_star_24 + "");
                    preferenceMap.put(String.valueOf(position + 1),true);
                }
                PowerPreference.getDefaultFile().putObject("preferenceMap",preferenceMap);

            }
        });
    }

    @Override
    public int getItemCount() {
        return siteList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;
        public ImageView img_start;
//        private ItemClickListener itemClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textView = itemView.findViewById(R.id.textView);
            img_start = itemView.findViewById(R.id.img_start);
//            text = itemView.findViewById(R.id.ex_name);
//            itemView.setOnClickListener(this);


        }


    }


}
