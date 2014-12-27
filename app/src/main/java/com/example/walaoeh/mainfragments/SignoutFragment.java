package com.example.walaoeh.mainfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.walaoeh.R;
import com.example.walaoeh.Splash;
import com.example.walaoeh.helper.Const;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;

public class SignoutFragment extends Fragment {

    private ProfilePictureView profilePicture;
    private TextView userNameText;
    private TextView stageTextView;
    private LoginButton logoutButton;
    private int stage;
    private UiLifecycleHelper uiHelper;

    public static SignoutFragment newInstance(int stageLevel) {
        SignoutFragment fragment = new SignoutFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Const.STAGE_KEY, stageLevel);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout, container, false);

        profilePicture = (ProfilePictureView) view.findViewById(R.id.profilePicture);
        userNameText = (TextView) view.findViewById(R.id.userNameText);
        stageTextView = (TextView) view.findViewById(R.id.stagetextview);
        stageTextView.setText(Const.STAGE_NAME[stage]);
        logoutButton = (LoginButton) view.findViewById(R.id.logoutButton);
        logoutButton.setFragment(this);

        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            // Get the user's data
            makeMeRequest(session);
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        stage = bundle.getInt(Const.STAGE_KEY);

        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        if (session != null && session.isOpened()) {
            // Get the user's data.
            makeMeRequest(session);
        } else if(session != null && session.isClosed()) {
            signout();
        }
    }

    private void makeMeRequest(final Session session) {
        // Make an API call to get user data and define a
        // new callback to handle the response.
        Request request = Request.newMeRequest(session,
            new Request.GraphUserCallback() {
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    // If the response is successful
                    if (session == Session.getActiveSession()) {
                        if (user != null) {
                            // Set the id for the ProfilePictureView
                            // view that in turn displays the profile picture.
                            profilePicture.setProfileId(user.getId());
                            // Set the Textview's text to the user's name.
                            userNameText.setText(user.getName());
                        }
                    }
                    if (response.getError() != null) {
                        // Handle errors, will do so later.
                    }
                }
            });
        request.executeAsync();
    }

    private void signout() {
        Intent intent = new Intent(getActivity(), Splash.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        uiHelper.onSaveInstanceState(bundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
}
