package com.stevanlim222102453.recyclerviewb;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton _addButton, _refreshButton;
    private RecyclerView _recyclerView1;
    private List<MahasiswaModel> _mahasiswaModelList;
    private MahasiswaAdapter ma;
    private TextView _txtMahasiswaCount, _txtSearch;
    private ImageButton _btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        _recyclerView1 = findViewById(R.id.recycleView1);
        _txtMahasiswaCount = findViewById(R.id.txtMahasiswaCount);

        loadRecyclerView();
        initAddButton();
        initRefreshButton();
        initSearch();
    }
    private void initSearch(){
        _txtSearch = findViewById(R.id.txtSearch);

        _txtSearch.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent){
                String filterText = _txtSearch.getText().toString();
                if (!filterText.isEmpty()){
                    filter(filterText);
                }
                else
                    loadRecylerView();
                return false;
            }
        });
    }
    private void filter(String text){
        List<MahasiswaModel> filteredList = new ArrayList<>();

        for (MahasiswaModel item: _mahasiswaModelList){
            if (item.getNama().toLowerCase().contains(text.toLowerCase())){
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                filteredList.add(item);
            } else {
                ma.filter(filteredList);
            }
        }
    }
}