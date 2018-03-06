package livecricket.cricketapplication.com.livecricket;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Amey on 11-Dec-17.
 */

public class SelectionPage extends AppCompatActivity{
    public ImageView cric_button;
    public ImageView ten_button;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_page_activity);

        cric_button = findViewById(R.id.Cric_button);
        ten_button = findViewById(R.id.Tennis_button);

        cric_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Cricket_list.class);
                startActivity(intent);
            }
        });



    }
}
