package com.example.homework1pam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemberDetailActivity extends AppCompatActivity {
    private Member member;  // The member being displayed
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        member=(Member) getIntent().getSerializableExtra("member");

        //displaying the member details

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewNameDetail = findViewById(R.id.textViewNameDetail);
        TextView textViewDescriptionDetail = findViewById(R.id.textViewDescriptionDetail);
        if (member != null) {
            // Use Picasso or Glide to load the image from a URL
            //Picasso.get().load(member.getImageUrl()).into(imageViewDetail);
            textViewNameDetail.setText(member.getName());
            textViewDescriptionDetail.setText(member.getShortDescription());
        } else {
            // Handle the case where there is no member object
        }

        // Set up the "More Details" button

        Button buttonMoreDetails = findViewById(R.id.buttonMoreDetails);
        buttonMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullDetailIntent = new Intent(MemberDetailActivity.this, MemberFullDetailActivity.class);
                fullDetailIntent.putExtra("member", member); // Pass the member object
                startActivity(fullDetailIntent);
            }
        });

        //Set up the "Delete" button
        db=new DatabaseHelper(this);
        db = new DatabaseHelper(this);
        member = (Member) getIntent().getSerializableExtra("member");

        FloatingActionButton fabDelete = findViewById(R.id.fab_delete_member);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeletion();  // Call to show confirmation dialog
            }
        });
    }

    private void confirmDeletion() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Do you really want to delete this member?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteMember();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void deleteMember() {
        if (member != null) {
            db.deleteMember(member);
            Toast.makeText(this, "Member deleted successfully!", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity and go back
        }
    }
}