package ladyjek.twiscode.com.ladyjek.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ladyjek.twiscode.com.ladyjek.R;
import ladyjek.twiscode.com.ladyjek.Utilities.DetectSoftwareKeyboard;
import ladyjek.twiscode.com.ladyjek.Utilities.Utilities;

public class ActivityLogin extends Activity implements DetectSoftwareKeyboard.Listener {

    private Activity act;
    private TextView btnRegister, btnLogin;
    private EditText txtEmail, txtPassword;
    private RelativeLayout wrapperLogin, wrapperRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        act = this;
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        wrapperLogin = (RelativeLayout) findViewById(R.id.wrapperLogin);
        wrapperRegister = (RelativeLayout) findViewById(R.id.wrapperRegister);


        DetectSoftwareKeyboard mainLayout = (DetectSoftwareKeyboard) findViewById(R.id.layoutLogin);
        mainLayout.setListener(this);

        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrapperLogin.setVisibility(View.VISIBLE);
                wrapperRegister.setVisibility(View.GONE);
            }
        });

        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrapperLogin.setVisibility(View.VISIBLE);
                wrapperRegister.setVisibility(View.GONE);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ActivityRegister.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
                    Utilities.showDialog(act,"Warning", "Email or Password is empty!");
                    txtEmail.setText("");
                    txtPassword.setText("");
                } else if (!email.trim().contains("@") ||
                        !email.trim().contains(".") ||
                        email.trim().contains(" ")) {
                    Utilities.showDialog(act, "Warning", "Wrong email format!");
                    txtEmail.setText("");
                    txtPassword.setText("");
                } else {
                    new DoLogin(act).execute(
                            email,
                            password
                    );
                }

                */
                Intent i = new Intent(getBaseContext(), ActivityTransport.class);
                startActivity(i);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSoftKeyboardShown(boolean isShowing) {
        if (isShowing) {
            wrapperLogin.setVisibility(View.VISIBLE);
            wrapperRegister.setVisibility(View.GONE);
        } else {
            wrapperLogin.setVisibility(View.GONE);
            wrapperRegister.setVisibility(View.VISIBLE);
        }
    }


    private class DoLogin extends AsyncTask<String, Void, String> {
        private Activity activity;
        private Context context;
        private Resources resources;
        private ProgressDialog progressDialog;

        private String tempString;

        public DoLogin(Activity activity) {
            super();
            this.activity = activity;
            this.context = activity.getApplicationContext();
            this.resources = activity.getResources();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Signing in. . .");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // Generates Basic Authorization Basic Header

            try {

                String email = params[0];
                String password = params[1];

                //Process login
                if (email.equals("edo@gmail.com") && password.equals("abcd1234")) {
                    return "OK";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "FAIL";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            switch (result) {
                case "FAIL":
                    Utilities.showDialog(activity, "Warning", "Login Failed!");
                    break;
                case "OK":
                    Intent i = new Intent(getBaseContext(), ActivityTransport.class);
                    startActivity(i);
                    finish();
                    break;
            }

            txtPassword.clearFocus();
            txtPassword.setText("");
        }


    }
}

