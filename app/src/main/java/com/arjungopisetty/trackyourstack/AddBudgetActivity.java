package com.arjungopisetty.trackyourstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBudgetActivity extends Activity {

    private EditText mBudgetValue, mBudgetName;
    private Button mAddNewBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        mBudgetName = (EditText) findViewById(R.id.budgetName);
        mBudgetValue = (EditText) findViewById(R.id.budgetValue);

        mAddNewBudget = (Button) findViewById(R.id.setNewBudget);
        mAddNewBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", mBudgetName.getText().toString());
                intent.putExtra("budgetValue", Double.parseDouble(mBudgetValue.getText().toString()));
                setResult(1, intent);
                Log.d("finished", "finished");
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
