package com.jonmid.asynctaskinternet.Adapter;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonmid.asynctaskinternet.Models.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aula7 on 21/09/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
//    el adapter sirve para inyectar vistas dentro de un componente
    List<Comment> commentList = new ArrayList<>();
    Context context;

//

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

//
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
//        toda clase adapter necesite un viewholder, es una clase que permite hacer referencia a los componentes
//        cuando se use recyclerview se debe usar un adapter
        ImageView imageView;
        TextView textViewEmail;
        TextView textViewBody;

        public ViewHolder(View item) {
            super(item);
        }
    }
}
