package com.example.homework1pam;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;

public class MemberCrudActivity extends AppCompatActivity {
    private EditText editTextName, editTextImageUrl, editTextShortDesc, editTextDetailedDesc;
    private Button buttonAddMember;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_crud);

        databaseHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextMemberName);
        //editTextImageUrl = findViewById(R.id.editTextMemberImageUrl);
        editTextShortDesc = findViewById(R.id.editTextMemberShortDescription);
        editTextDetailedDesc = findViewById(R.id.editTextMemberDetailedDescription);
        buttonAddMember = findViewById(R.id.buttonAddMember);

        buttonAddMember.setOnClickListener(view -> {
            String name = editTextName.getText().toString().trim();
            String imageUrl = editTextImageUrl.getText().toString().trim();
            String shortDesc = editTextShortDesc.getText().toString().trim();
            String detailedDesc = editTextDetailedDesc.getText().toString().trim();

            Member newMember = new Member(name, imageUrl, shortDesc, detailedDesc);
            databaseHelper.addMember(newMember);

            // Optionally reset the input fields and/or provide feedback
            editTextName.setText("");
            editTextImageUrl.setText("");
            editTextShortDesc.setText("");
            editTextDetailedDesc.setText("");
        });
    }
}