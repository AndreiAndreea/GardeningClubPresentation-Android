package com.example.homework1pam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MemberViewHolder> {

    private Context mContext;
    private List<Member> memberList;

    public MembersAdapter(Context mContext, List<Member> memberList) {
        this.mContext = mContext;
        this.memberList = memberList;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.textViewName.setText(member.getName());
        holder.textViewShortDesc.setText(member.getShortDescription());
        // Here you could use an image loading library like Glide or Picasso to load images from URLs
    //Handle the item click
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(mContext, MemberDetailActivity.class);
                detailIntent.putExtra("member", member); // Make sure Member class implements Serializable
                mContext.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewShortDesc;

        public MemberViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewMember);
            textViewName = itemView.findViewById(R.id.textViewMemberName);
            textViewShortDesc = itemView.findViewById(R.id.textViewMemberShortDesc);
        }
    }
}

