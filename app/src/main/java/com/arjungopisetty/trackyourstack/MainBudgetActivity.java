package com.arjungopisetty.trackyourstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainBudgetActivity extends Activity {

    private ListView mItemList;
    private ArrayList<Budget> mItems;
    private ArrayAdapter mArrayAdapter;
    private double mMyTotal;
    private TextView mItemTotal;
    private Button mNewItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        Intent intent = getIntent();
        mMyTotal = intent.getDoubleExtra("value",0);
        mItems = new ArrayList<Budget>();
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mItems);
        mItemList = (ListView) findViewById(R.id.itemList);
        mItemList.setAdapter(mArrayAdapter);
        mItemTotal = (TextView) findViewById(R.id.budgetTotal);

        mNewItem = (Button) findViewById(R.id.setNewItem);
        mNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddBudgetActivity.class);
                startActivityForResult(intent, 2);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        //Grabs the value of the budget to add to the ListView when the "plus" button is clicked on the main activity
        if (resultCode == 2) {
            String text = data.getStringExtra("name");
            Double value = data.getDoubleExtra("value", 0);
            Budget myBudget = new Budget(text,value);
            mItems.add(myBudget);
            mMyTotal -= value;
            String str = mMyTotal + "";
            mItemTotal.setText(str);

//            mData.add(text);
//            mDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
