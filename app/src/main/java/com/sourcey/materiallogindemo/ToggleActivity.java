package com.sourcey.materiallogindemo;

/**
 * Created by thenu on 19-12-2017.
 */





        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.Toast;
        import android.widget.ToggleButton;

public class ToggleActivity extends Activity {
    private ToggleButton toggleButton1;
    private Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        //Getting the ToggleButton and Button instance from the layout xml file
        toggleButton1=(ToggleButton)findViewById(R.id.toggleButton1);

        buttonSubmit=(Button)findViewById(R.id.toggleButton1);

        //Performing action on button click
        buttonSubmit.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder result = new StringBuilder();
                result.append("ToggleButton1 : ").append(toggleButton1.getText());

                //Displaying the message in toast
                Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
