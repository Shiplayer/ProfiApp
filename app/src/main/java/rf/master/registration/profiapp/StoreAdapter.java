package rf.master.registration.profiapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import rf.master.registration.profiapp.data.entity.Store;

/**
 * Created by Shiplayer on 28.03.18.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private List<Store> mListStories;

    public StoreAdapter(List<Store> stories){
        mListStories = stories;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView storePhoto;
        public TextView firstName;
        public TextView lastName;
        public RatingBar storiesRating;
        public RatingBar costRating;
        public TextView tvCostShow;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_stories_list);
            storePhoto = itemView.findViewById(R.id.iv_store_photo);
            firstName = itemView.findViewById(R.id.tv_firstName);
            lastName = itemView.findViewById(R.id.tv_lastName);
            storiesRating = itemView.findViewById(R.id.rb_stories);
            costRating = itemView.findViewById(R.id.rb_costs);
            tvCostShow = itemView.findViewById(R.id.tv_cost_show);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_contain, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.storePhoto.setImageDrawable(mListStories.get(position).getTitleImage());
        holder.firstName.setText(mListStories.get(position).getFirstNameManager());
        holder.lastName.setText(mListStories.get(position).getLastNameManager());
        holder.storiesRating.setRating(mListStories.get(position).getShopRating());
        holder.costRating.setRating(mListStories.get(position).getCostRating());
        holder.tvCostShow.setText(String.valueOf((float) Math.round(mListStories.get(position).getCostRating()*10)/10));
    }

    @Override
    public int getItemCount() {
        return mListStories.size();
    }

    public void changeStories(List<Store> stories){
        this.mListStories = stories;
        notifyDataSetChanged();
    }
}
