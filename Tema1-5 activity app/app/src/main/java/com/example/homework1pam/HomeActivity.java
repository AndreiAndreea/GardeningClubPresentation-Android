package com.example.homework1pam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnViewMembers = findViewById(R.id.buttonViewMembers);
        btnViewMembers.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnViewWebInfo = findViewById(R.id.buttonViewWebInfo);
        btnViewWebInfo.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, WebInfoActivity.class);
            startActivity(intent);
        });
    }
}