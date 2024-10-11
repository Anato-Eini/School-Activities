package com.example.client.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.client.Activities.OrganizerDashboard;
import com.example.client.Activities.UserDashboard;
import com.example.client.MetroEvents;
import com.example.client.R;
import com.example.client.SocketClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button login_btn_ = view.findViewById(R.id.login_btn_);
        EditText username_et_ = view.findViewById(R.id.username_et_);
        EditText password_et_ = view.findViewById(R.id.password_et_);

        login_btn_.setOnClickListener(v -> {
            String username = username_et_.getText().toString().trim();
            String password = password_et_.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            SocketClient.login(username, password, (status, user) -> {
                assert getActivity() != null;
                getActivity().runOnUiThread(() -> {
                    if (status && user != null) {
                        MetroEvents metroEvents = (MetroEvents) getActivity().getApplication();
                        metroEvents.saveUser(user);

                        Intent intent = null;
                        switch (user.privilege) {
                            case USER:
                                intent = new Intent(getActivity(), UserDashboard.class);
                                break;
                            case ORGANIZER:
                                intent = new Intent(getActivity(), OrganizerDashboard.class);
                                break;
                            case ADMIN:
                                // TODO: Implement ADMIN dashboard
//                                Toast.makeText(getContext(), "Admin dashboard not implemented yet", Toast.LENGTH_SHORT).show();
                                return;
                        }

                        if (intent != null) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            });
        });

        TextView navigate_register_btn = view.findViewById(R.id.navigate_register_btn_);

        navigate_register_btn.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, Register.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("register")
                    .commit();
        });
        return view;
    }
}