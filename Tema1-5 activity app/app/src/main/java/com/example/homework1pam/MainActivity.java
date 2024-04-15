package com.example.homework1pam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
        recyclerViewMembers = findViewById(R.id.recyclerViewMembers);
        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list
        memberList = new ArrayList<>();
        adapter = new MembersAdapter(this, memberList);
        recyclerViewMembers.setAdapter(adapter);

        // Add some test members if needed and fetch the initial member list
        addTestMembers();
        updateMemberList();

        FloatingActionButton fab = findViewById(R.id.fab_add_member);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MemberCrudActivity.class);
            startActivity(intent);
        });
    }

    private void addTestMembers() {
        // Check if members already exist to avoid adding duplicates every time the app opens
        if (db.getAllMembers().isEmpty()) {
            Member newMember = new Member("Jane Doe", "", "Loves succulents", "Expert in desert plants.");
            db.addMember(newMember);
        }
    }

    private void updateMemberList() {
        memberList.clear();
        memberList.addAll(db.getAllMembers());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMemberList();  // Refresh the list every time the activity resumes
    }
}
