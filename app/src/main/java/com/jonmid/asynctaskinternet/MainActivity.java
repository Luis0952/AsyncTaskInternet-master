package com.jonmid.asynctaskinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jonmid.asynctaskinternet.Adapter.CommentAdapter;
import com.jonmid.asynctaskinternet.Models.Comment;
import com.jonmid.asynctaskinternet.Parser.Json;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    Parser --> json es el Poujo
    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;

//    en la variable commentList se almacena todos los 500 registros
    List <Comment> commentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_1);
        button = (Button) findViewById(R.id.id_btn_1);
        recyclerView=(RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView=(RecyclerView) findViewById(R.id.id_rv_item);
    }

    // Metodo para validar la conexion a internet
    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(View view){
        if (isOnLine()){
            // Hacer llamado a la tarea
            MyTask task = new MyTask();
            task.execute("https://jsonplaceholder.typicode.com/comments");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        // textView.setText("item: "+s);
        // textView.setTextSize(Integer.parseInt(s));
        commentAdapter=new CommentAdapter(commentList, getApplicationContext());
//        Tiene toda la configuracion
        recyclerView.setAdapter(commentAdapter);

    }

    public class MyTask extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            // Traer datos desde internet
            /*for (int i = 1; i < 50; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(String.valueOf(i));
            }
            return "fin";*/

            String contend = null;
            try {
                contend = HttpManager.getData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contend;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
//                aqui estan los 500 regsitros
                commentList= Json.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.GONE);
            processData();
        }
    }
}
