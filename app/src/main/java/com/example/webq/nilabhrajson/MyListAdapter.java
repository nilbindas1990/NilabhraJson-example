package com.example.webq.nilabhrajson;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListAdapter extends ArrayAdapter<MyJson> {
    private List<MyJson> myJsonList;
    private Context context;

    public MyListAdapter(List<MyJson> myJsonList, Context context) {
        super(context, R.layout.list_item, myJsonList);
        this.myJsonList = myJsonList;
        this.context = context;

    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        final MyJson item = this.getItem(position);
        //Creating view with xml layout
        final View listViewItem = layoutInflater.inflate(R.layout.list_item, null, true);

        final TextView u_id = listViewItem.findViewById(R.id.u_id);
        final TextView txtName = listViewItem.findViewById(R.id.name);
        TextView txtEmail = listViewItem.findViewById(R.id.email);
        //Button button = listViewItem.findViewById(R.id.button);


        final MyJson myJson = myJsonList.get(position);

        u_id.setText(myJson.getId());
        txtName.setText(myJson.getName());
        txtEmail.setText(myJson.getEmail());

        Button button = listViewItem.findViewById(R.id.button);
        Button delete = listViewItem.findViewById(R.id.delete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("BTN", "Button Clicked");
                Intent updateIntent = new Intent(getContext() , UpdateUser.class);
                updateIntent.putExtra("id" , u_id.getText().toString());
                context.startActivity(updateIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Position", String.valueOf(txtName.getText()));
                Toast.makeText(getContext(), "Delete Clicked", Toast.LENGTH_SHORT).show();
                String currentUser_Id = u_id.getText().toString();
                if (deleteProduct(currentUser_Id)){
                    remove(item);
                }

            }
        });

        return listViewItem;
    }

    private boolean deleteProduct(final String currentUser_Id) {
        //Log.d("deletId", currentUser_Id);
        String deleteUrl = "https://webqueuesolution.com/samples/projects/sandip/android_development/test_api/delete.php";

        RequestQueue deleteRequestQueue = Volley.newRequestQueue(getContext());

        StringRequest deleteRequest = new StringRequest(Request.Method.POST , deleteUrl,
                    new Response.Listener<String>(){

                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> params=new HashMap<>();
                            params.put("id",currentUser_Id);
                            return params;

                        }
                    };

                    deleteRequestQueue.add(deleteRequest);
        return true;
    }
}
