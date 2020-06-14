package com.dogypedia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.dogypedia.R;

public class ContactActivity extends Fragment {
    private EditText email;
    private EditText subject;
    private EditText body;
    private Button send;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View routeView = inflater.inflate(R.layout.contact_fragment, container, false);
        email = routeView.findViewById(R.id.email_edit);
        subject = routeView.findViewById(R.id.subject_edit);
        body = routeView.findViewById(R.id.body_edit);
        send = routeView.findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                startActivity(intent);
            }
        });
        return routeView;
    }

}
