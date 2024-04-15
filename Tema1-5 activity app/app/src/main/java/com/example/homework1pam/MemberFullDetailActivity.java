package com.example.homework1pam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberFullDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member_full_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize your views, e.g. TextViews for each member attribute
        TextView textViewName = findViewById(R.id.textViewFullName);
        ImageView imageView = findViewById(R.id.imageViewFull);

        TextView textViewShortDescription = findViewById(R.id.textViewFullShortDescription);
        TextView textViewDetailedDescription = findViewById(R.id.textViewFullDetailedDescription);

        // Retrieve the Member object from the intent
        Member member = (Member) getIntent().getSerializableExtra("member");
        if (member != null) {
            textViewName.setText(member.getName());
            // Use Picasso or Glide to load the image from a URL
            textViewShortDescription.setText(member.getShortDescription());
            textViewDetailedDescription.setText(member.getDetailedDescription());
        } else {
            // Handle the error, e.g., finish the activity or show a message
        }
    }
}