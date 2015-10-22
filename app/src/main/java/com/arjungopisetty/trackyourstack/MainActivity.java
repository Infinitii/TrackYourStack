package com.arjungopisetty.trackyourstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    public Double mMyTotal;
    private TextView mGrandTotal;
    private ListView mBudgetList;

    private ArrayList<Budget> mBudgetItems;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBudgetItems = new ArrayList<Budget>();
        //  mBudgetItems.add(new Budget("hi", 100));
        //  mBudgetItems.add(new Budget("hi2", 200));
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mBudgetItems);
        mBudgetList = (ListView) findViewById(R.id.budgetList);
        System.out.println("mBudgetList: " + mBudgetList);
        mBudgetList.setAdapter(mArrayAdapter);
        //**Hardcoded to 10k, change later **
        mMyTotal = 10000.0;
        mGrandTotal = (TextView) findViewById(R.id.grandTotal);
        mGrandTotal.setText(mMyTotal + "");

        mBudgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), ManageBudgetActivity.class);
                Budget myBudget = (Budget) mBudgetItems.get(position);
                intent.putExtra("value", myBudget.getValue());
                setResult(3,intent);
                startActivity(intent);
            }
        });
    }
    // Updates the total budget
    public void updateTotal(Double aTotal){
        mMyTotal = aTotal;
        String str = mMyTotal + "";
        mGrandTotal.setText(str);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        // Grabs the value of the budget to add to the ListView when the "plus" button is clicked on the main activity
        if (resultCode == 1) {
            String text = data.getStringExtra("name");
            Double value = data.getDoubleExtra("budgetValue", 20);
            Log.d("value", value + "");
            Budget myBudget = new Budget(text, value);
            mBudgetItems.add(myBudget);
            mMyTotal -= value;
            String str = mMyTotal + "";
            mGrandTotal.setText(str);
            mArrayAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.addNewBudget) {
            Intent intent = new Intent(getApplicationContext(), AddBudgetActivity.class);
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }
}
