package com.example.homework1pam;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewMembers;
    MembersAdapter adapter;
    List<Member> memberList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        // Add some test members
        addTestMembers();

        // Fetch and display members from the database
        List<Member> memberList = db.getAllMembers(); // Fetch the members
        RecyclerView recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));
        MembersAdapter adapter = new MembersAdapter(this, memberList);
        recyclerViewMembers.setAdapter(adapter);
    }

    private void addTestMembers() {
            // Check if members already exist to avoid adding duplicates every time the app opens
            if (db.getAllMembers().isEmpty()) {
                Member newMember = new Member("Jane Doe", "", "Loves succulents", "Expert in desert plants.");
                db.addMember(newMember);
            }

    }

}
