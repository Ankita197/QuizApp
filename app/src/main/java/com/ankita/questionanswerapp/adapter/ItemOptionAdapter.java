package com.ankita.questionanswerapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ankita.questionanswerapp.MainActivity;
import com.ankita.questionanswerapp.R;
import com.ankita.questionanswerapp.model.Option;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemOptionAdapter extends RecyclerView.Adapter<ItemOptionAdapter.ViewHolder> {

    int selectedPos=-1,answer=-1,wrongAnswerPosition=-1;
    Context context;
    ArrayList<Option> optionArrayList;
    OnItemClick onItemClick;
    HashMap<Integer ,Integer> mapQuesAnswer=new HashMap<>();

    public ItemOptionAdapter(Context context) {
        this.context = context;
    }
    public void setList(ArrayList<Option> arrayList){
        this.optionArrayList=arrayList;
    }



    public void setClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvOptionNumber.setText(optionArrayList.get(position).getOptNumber());
        holder.tvOptionDescription.setText(optionArrayList.get(position).getOptValue());
        if(answer==position){
            holder.itemView.setSelected(true);
            holder.itemLayout.setCardBackgroundColor(Color.parseColor("#43E616"));
            holder.tvOptionNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvOptionDescription.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rbSelector.setButtonDrawable(R.drawable.rb_check_icon);
        }
        else if(wrongAnswerPosition==position){
            holder.itemView.setSelected(true);
            holder.itemLayout.setCardBackgroundColor(Color.parseColor("#F84911"));
            holder.tvOptionNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvOptionDescription.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rbSelector.setButtonDrawable(R.drawable.rb_check_icon);
        }

       else if(selectedPos==position){
            holder.itemView.setSelected(true);
            holder.itemLayout.setCardBackgroundColor(Color.parseColor("#00b386"));
            holder.tvOptionNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvOptionDescription.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rbSelector.setButtonDrawable(R.drawable.rb_check_icon);
        }
        else {
            holder.itemView.setSelected(false);
            holder.itemLayout.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvOptionNumber.setTextColor(Color.parseColor("#000000"));
            holder.tvOptionDescription.setTextColor(Color.parseColor("#000000"));
            holder.rbSelector.setButtonDrawable(R.drawable.round_icon);
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
                        public void onClick(View v) {
                onItemClick.OnClick(holder,position);
                selectedPos=position;

            }
        });
        holder.rbSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.OnClick(holder,position);
                selectedPos=position;
            }
        });
    }
    public void selectedItem() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return optionArrayList.size();
    }

    public void setMapQuesAnswer(HashMap<Integer, Integer> mapQuesAnswer) {
        this.mapQuesAnswer = mapQuesAnswer;
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getWrongAnswerPosition() {
        return wrongAnswerPosition;
    }

    public void setWrongAnswerPosition(int wrongAnswerPosition) {
        this.wrongAnswerPosition = wrongAnswerPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOptionNumber,tvOptionDescription;
        CardView itemLayout;
        RadioButton rbSelector;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOptionNumber=itemView.findViewById(R.id.tvOptionNumber);
            tvOptionDescription=itemView.findViewById(R.id.tvOptionDescription);
            itemLayout=itemView.findViewById(R.id.itemLayout);
            rbSelector=itemView.findViewById(R.id.rbSelector);
        }
    }
}
