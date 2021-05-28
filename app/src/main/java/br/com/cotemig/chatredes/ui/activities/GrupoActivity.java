package br.com.cotemig.chatredes.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.cotemig.chatredes.R;
import br.com.cotemig.chatredes.ui.adapters.GrupoAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.GroupsRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GrupoActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, GrupoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        getGrupo();
    }

    private void getGrupo() {
        GroupsRequest groupsRequest = new GroupsRequest.GroupsRequestBuilder().joinedOnly(true).build();
        groupsRequest.fetchNext(new CometChat.CallbackListener<List<Group>>() {
            @Override
            public void onSuccess(List<Group> list) {
                attInterface(list);
            }

            @Override
            public void onError(CometChatException e) {

            }
        });
    }

    private void attInterface(List<Group> list) {
        RecyclerView recyclerGrupo = findViewById(R.id.recyclerGrupo);
        recyclerGrupo.setLayoutManager(new LinearLayoutManager(this));
        GrupoAdapter grupoAdapter = new GrupoAdapter(list, this);
        recyclerGrupo.setAdapter(grupoAdapter);
    }
}
