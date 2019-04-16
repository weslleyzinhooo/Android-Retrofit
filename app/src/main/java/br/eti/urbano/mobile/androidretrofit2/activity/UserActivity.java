package br.eti.urbano.mobile.androidretrofit2.activity;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.jar.Attributes;

import br.eti.urbano.mobile.androidretrofit2.R;
import br.eti.urbano.mobile.androidretrofit2.bootstrap.APIClient;
import br.eti.urbano.mobile.androidretrofit2.model.Address;
import br.eti.urbano.mobile.androidretrofit2.model.User;
import br.eti.urbano.mobile.androidretrofit2.resource.UserResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    UserResource apiUserResouce;

    EditText txtId;
    EditText txtUserName;
    EditText txtData;
    ListView listViewUser;
    List<User> listUser;
    List<HashMap<String,String>> colecao =
            new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //tem o contexto da aplicação (application)
        //PASSO 4
        apiUserResouce = APIClient.getClient().create(UserResource.class);

        Call<List<User>> get = apiUserResouce.get();

        get.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listViewUser = findViewById(R.id.listViewUser);

                listUser = response.body();

                listUser.forEach(u ->{
                    //Criar dados para adapter
                    HashMap<String,String> mapUser = new HashMap<String,String>();
                    mapUser.put("id",String.valueOf(u.getId()));
                    mapUser.put("username",u.getUsername());

                    colecao.add(mapUser);
                });

                String[] from = {"id","username"};
                int[] to = {R.id.txtId,R.id.txtUserName, R.id.textView};

                SimpleAdapter simpleAdapter =
                        new SimpleAdapter(
                                getApplicationContext(),
                                colecao,
                                R.layout.user,
                                from,
                                to);

                listViewUser.setAdapter(simpleAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addUser(View view) {

        String username,data;
        Integer id;
        id = Integer.parseInt(txtId.getText().toString());
        username = txtUserName.getText().toString();
        data = txtData.getText().toString();



        final User user = User.builder()
                .id(id)
                .name("")
                .username(username)
                .email("")

                .build();

        Call<User> post = apiUserResouce.post(user);
        post.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                Toast.makeText(getApplicationContext(),
                        u.toString(),
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        Call<User> put = apiUserResouce.put(user);
        put.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Call<Void> delete = apiUserResouce.delete(user);
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
