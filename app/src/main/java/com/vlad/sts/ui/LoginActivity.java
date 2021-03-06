package com.vlad.sts.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.vlad.sts.R;
import com.vlad.sts.data.events.login.LoginEvent;
import com.vlad.sts.data.events.login.RegistrationEvent;
import com.vlad.sts.engine.eventbus.EBActivity;
import com.vlad.sts.engine.firebase.FirebaseAuthHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends EBActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText email;
    private EditText password;
    private EditText userName;

    // player
    //public static Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        userName = (EditText) findViewById(R.id.et_userName);

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);

       // player = new Player();
    }

    @Override
    public void onClick(View view) {
        try {
            String em = email.getText().toString();
            String pass = password.getText().toString();
            String name = userName.getText().toString();
            if (view.getId() == R.id.btn_sign_in) {
                // заглушка для тестов
                if (TextUtils.isEmpty(em) || TextUtils.isEmpty(pass)) {
                    // accounts for tests
                    //FirebaseAuthHelper.getInstance().signIn("test5@t.ru", "12345678", "");
                    //FirebaseAuthHelper.getInstance().signIn("test6@t.io", "12345678", "");
                    //FirebaseAuthHelper.getInstance().signIn("test7@t.io", "12345678", "");

                    Toast.makeText(LoginActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuthHelper.getInstance().signIn(em, pass, "");
                }
            } else if (view.getId() == R.id.btn_registration) {
                if (TextUtils.isEmpty(name))
                    Toast.makeText(LoginActivity.this, "Empty Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(em))
                    Toast.makeText(LoginActivity.this, "Empty Email", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "Empty Password", Toast.LENGTH_SHORT).show();
                else FirebaseAuthHelper.getInstance().registration(em, pass, name);
            }
        } catch (Exception e) {
            Log.e(TAG,"Registration error",e);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSignIn(LoginEvent event) {
        if (event.isSucceed()) {
            Toast.makeText(LoginActivity.this, R.string.login_authorisation_success_toast_message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, R.string.login_authorisation_failed_toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegister(RegistrationEvent event) {
        if (event.isSucceed()) {
            Toast.makeText(LoginActivity.this, R.string.login_registration_success_toast_message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, R.string.login_registration_failed_toast_message, Toast.LENGTH_SHORT).show();
        }
    }
}

