package vendas.app.com.br.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import vendas.app.com.br.R;

public class MainActivity extends AppCompatActivity {

    private ImageView img_configs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_configs = findViewById(R.id.img_configs);
        img_configs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParametrosActivity.class);
                startActivity(intent);
            }
        });


    }
}
