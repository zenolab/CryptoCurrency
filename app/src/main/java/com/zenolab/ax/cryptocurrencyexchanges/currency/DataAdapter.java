package com.zenolab.ax.cryptocurrencyexchanges.currency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zenolab.ax.cryptocurrencyexchanges.R;

class DataAdapter{}
//class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
//
//    private LayoutInflater inflater;
//    private List<Phone> phones;
//
//    DataAdapter(Context context, List<Phone> phones) {
//        this.phones = phones;
//        this.inflater = LayoutInflater.from(context);
//    }
//    @Override
//    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = inflater.inflate(R.layout.list_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
//        Phone phone = phones.get(position);
//        holder.imageView.setImageResource(phone.getImage());
//        holder.nameView.setText(phone.getName());
//        holder.companyView.setText(phone.getCompany());
//    }
//
//    @Override
//    public int getItemCount() {
//        return phones.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        final ImageView imageView;
//        final TextView nameView, companyView;
//        ViewHolder(View view){
//            super(view);
//            imageView = (ImageView)view.findViewById(R.id.image);
//            nameView = (TextView) view.findViewById(R.id.name);
//            companyView = (TextView) view.findViewById(R.id.company);
//        }
//    }
//}