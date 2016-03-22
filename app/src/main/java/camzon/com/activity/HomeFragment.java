package camzon.com.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camzon.com.R;
import camzon.com.adapter.AdapterHome;
import camzon.com.app.AppController;
import camzon.com.model.Home;
import camzon.com.utils.Constant;

/**
 * Created by Thuon on 3/15/2016.
 */
public class HomeFragment extends Fragment implements AdapterHome.ClickListener {
    RecyclerView recyclerView;
    public static AdapterHome adapter;
    private ProgressDialog pDialog;
    List<Home> homes = new ArrayList<>();
    Home home;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterHome(getActivity(), homes);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        showpDialog();

        if (homes.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_HOME, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            home = new Home();
                            home.setImage(Constant.URL_IMAGE + obj.getString("path"));
                            home.setTitle(obj.getString("title"));
                            home.setDescription(obj.getString("description"));
                            home.setTime(obj.getString("created_at"));
                            homes.add(home);
                            hidePDialog();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), "home" + volleyError, Toast.LENGTH_SHORT).show();
                    hidePDialog();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void itemClicked(View view, int position) {
//        Fragment fragment = new WebViewFragement();
//        Bundle bundle = new Bundle();
//        bundle.putString("url", "http://www.camzons.com/turn-your-old-laptop-to-touch-screen-with-airbar/");
//        fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.layoutHome,fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        Intent intent = new Intent(getActivity(),WebView.class);
        intent.putExtra("url", "http://www.camzons.com/turn-your-old-laptop-to-touch-screen-with-airbar/");
        startActivity(intent);
    }

    public void showpDialog(){
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}