package com.example.homework1pam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewNameDetail = findViewById(R.id.textViewNameDetail);
        TextView textViewDescriptionDetail = findViewById(R.id.textViewDescriptionDetail);

        // Assuming you're passing the member object as a serialized extra
        Member member = (Member) getIntent().getSerializableExtra("member");

        if (member != null) {
            // Use Picasso or Glide to load the image from a URL
            // Picasso.get().load(member.getImageUrl()).into(imageViewDetail);
            textViewNameDetail.setText(member.getName());
            textViewDescriptionDetail.setText(member.getShortDescription());
        } else {
            // Handle the case where there is no member object
        }
    }
}