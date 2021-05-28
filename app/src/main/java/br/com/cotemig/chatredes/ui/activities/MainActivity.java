package br.com.cotemig.chatredes.ui.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.google.android.material.snackbar.Snackbar;

import br.com.cotemig.chatredes.R;
import br.com.cotemig.chatredes.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCometChat();
        initViews();
    }
    private void initViews(){
        EditText usuarioLogin = findViewById(R.id.usuarioLogin);
        Button botaoLogin = findViewById(R.id.botaoLogin);
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CometChat.login(usuarioLogin.getText().toString(), Constants.authKey, new CometChat.CallbackListener<User>() {
                    @Override
                    public void onSuccess(User user) {
                        telaPrincipal();
                    }

                    @Override
                    public void onError(CometChatException e) {
                        botaoLogin.setText("NAO LOGOU");
                    }
                });
            }
        });
    }

    private void telaPrincipal(){
        GrupoActivity.start(this);
    }

    private void initCometChat(){
        AppSettings appSettings=new AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(Constants.region).build();

        CometChat.init(this, Constants.appID,appSettings, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
            }

            @Override
            public void onError(CometChatException e) {
            }
        });
    }
}