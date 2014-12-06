package com.arjungopisetty.trackyourstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ManageBudgetActivity extends Activity {

    private Double mMyTotal;
    private TextView mItemTotal;
    private ListView mItemList;

    private ArrayList<Budget> mItems;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_budget);

        //Intent intent = getIntent();
        //mMyTotal = intent.getDoubleExtra("value", 0);
        mItems = new ArrayList<Budget>();
        mItems.add(new Budget("high3", 200));
        mItems.add(new Budget("high4", 300));
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mItems);
        mItemList = (ListView) findViewById(R.id.itemList);
        System.out.println("mItemList: " + mItemList);
        mItemList.setAdapter(mArrayAdapter);

        mItemTotal = (TextView) findViewById(R.id.budgetTotal);

//        mItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(getApplicationContext(), ManageBudgetActivity.class);
//                Budget myBudget = (Budget) mItems.get(position);
//                intent.putExtra("value", myBudget.getValue());
//                startActivity(intent);
//            }
//        });

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

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.addNewItem) {
            Intent intent = new Intent(getApplicationContext(), AddBudgetActivity.class);
            startActivityForResult(intent, 2);
        }

        return super.onOptionsItemSelected(item);
    }
}
