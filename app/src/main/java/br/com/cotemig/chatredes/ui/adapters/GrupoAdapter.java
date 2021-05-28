package br.com.cotemig.chatredes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.models.Group;

import java.util.List;

import br.com.cotemig.chatredes.R;
import br.com.cotemig.chatredes.ui.activities.ChatActivity;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.GroupViewHolder> {

    private List<Group> grupos;
    private Context context;

    public GrupoAdapter(List<Group> groups, Context context) {
        this.grupos = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupViewHolder(LayoutInflater.from(context).inflate(R.layout.grupo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.bind(grupos.get(position));
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public  class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView nomeGrupo;
        LinearLayout layoutGeral;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeGrupo = itemView.findViewById(R.id.nomeGrupo);
            layoutGeral = itemView.findViewById(R.id.layoutGeral);
        }

        public void bind(Group group) {
            nomeGrupo.setText(group.getName());
            layoutGeral.setOnClickListener(v -> {
                ChatActivity.start(context, group.getGuid());
            });
        }
    }
}
