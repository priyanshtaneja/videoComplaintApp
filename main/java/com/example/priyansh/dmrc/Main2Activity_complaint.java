package com.example.priyansh.dmrc;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static android.widget.Toast.*;


public class Main2Activity_complaint extends AppCompatActivity {
    private Button play;
    private Button record;
    private Button submit;
    private VideoView mVideo;
    private int ACTIVITY_START_CAMERA_APP=0;
    private EditText compaint;
    private EditText name;
    DatabaseReference reff;
    Member member;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_complaint);

        compaint=(EditText) findViewById(R.id.complaint);
        name=(EditText) findViewById(R.id.name);
        play=(Button)findViewById(R.id.play);
        record=(Button)findViewById(R.id.record);
        submit=(Button)findViewById(R.id.submit);
        mVideo=(VideoView)findViewById(R.id.vid);
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        member=new Member();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playvid =new Intent();
                playvid.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(playvid,ACTIVITY_START_CAMERA_APP);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideo.start();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             member.setName(name.getText().toString().trim());
             member.setCompaint(compaint.getText().toString().trim());
            reff.child(String.valueOf(maxid+1)).setValue(member);
                Toast.makeText(Main2Activity_complaint.this, "Data inserted successfully.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==ACTIVITY_START_CAMERA_APP && resultCode==RESULT_OK){
            Uri vidURI=data.getData();
            mVideo.setVideoURI(vidURI);
        }
    }



}
