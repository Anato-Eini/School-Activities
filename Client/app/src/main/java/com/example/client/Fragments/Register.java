package com.example.client.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.client.R;
import com.example.client.SocketClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);


        EditText username_et = view.findViewById(R.id.username_et_);
        EditText password_et = view.findViewById(R.id.password_et_);
        EditText first_name_et = view.findViewById(R.id.first_name_et);
        EditText last_name_et = view.findViewById(R.id.last_name_et);
        Button register_btn = view.findViewById(R.id.register_btn);

        register_btn.setOnClickListener(v -> {
            String username = String.valueOf(username_et.getText());
            String password = String.valueOf(password_et.getText());
            String firstName = String.valueOf(first_name_et.getText());
            String lastName = String.valueOf(last_name_et.getText());
            SocketClient.registerAsync(username, password, firstName, lastName);
        });

        Button btnNavigateRegister = view.findViewById(R.id.navigate_login_btn_);
        btnNavigateRegister.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, Login.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("login")
                    .commit();
        });

        return view;
    }
}