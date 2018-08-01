package rf.master.registration.profiapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import rf.master.registration.profiapp.R;

/**
 * Created by Shiplayer on 08.04.18.
 */

public class ItemChooseAdapter extends RecyclerView.Adapter<ItemChooseAdapter.ViewHolder>{
    private List<String> mListNames;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mListNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mListNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_catalog_name);
        }
    }

    public void setData(List<String> data){
        mListNames = data;
        notifyDataSetChanged();
    }

    public void setData(String[] data){
        mListNames = Arrays.asList(data);
        notifyDataSetChanged();
    }
}
