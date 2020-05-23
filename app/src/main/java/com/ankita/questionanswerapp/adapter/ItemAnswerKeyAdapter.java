package com.ankita.questionanswerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ankita.questionanswerapp.R;
import com.ankita.questionanswerapp.model.Option;

import java.util.ArrayList;

public class ItemAnswerKeyAdapter extends RecyclerView.Adapter<ItemAnswerKeyAdapter.ViewHolder> {
    Context context;
    ArrayList<Option> arrayList;

    public ItemAnswerKeyAdapter(Context context, ArrayList<Option> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answerkey,parent,false);
        return new ItemAnswerKeyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.tvQuestion.setText(arrayList.get(position).getOptNumber());
    holder.tvAnswer.setText(arrayList.get(position).getOptValue());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvQuestion,tvAnswer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer=itemView.findViewById(R.id.tvAnswer);
            tvQuestion=itemView.findViewById(R.id.tvQuestion);
        }
    }
}
