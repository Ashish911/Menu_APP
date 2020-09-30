package com.example.menu_app.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_app.AboutUsActivity;
import com.example.menu_app.EditProfileActivity;
import com.example.menu_app.HelpActivity;
import com.example.menu_app.MainActivity;
import com.example.menu_app.R;
import com.example.menu_app.api.UsersAPI;
import com.example.menu_app.model.Users;
import com.example.menu_app.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    ImageButton editprofile;
    ImageView imgChief, imgWaiter;
    TextView UserName, Email;
    LinearLayout Aboutus, Help, Logout;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Aboutus = view.findViewById(R.id.AboutUs);
        Help = view.findViewById(R.id.Help);
        Logout = view.findViewById(R.id.LogOut);
        UserName = view.findViewById(R.id.AccUsername);
        Email = view.findViewById(R.id.AccEmail);
        imgChief = view.findViewById(R.id.imageChief);
        imgWaiter = view.findViewById(R.id.imageWaiter);
        editprofile = view.findViewById(R.id.editProfile);

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        Aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear().apply();
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<Users> usersCall = usersAPI.getUserDetails(Url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    UserName.setText(response.body().getUserName());
                    Email.setText(response.body().getEmail());
                    if (response.body().isChief() == true){
                        imgWaiter.setVisibility(View.INVISIBLE);
                    } else if (response.body().isWaiter() == true){
                        imgChief.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
