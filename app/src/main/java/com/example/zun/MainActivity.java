package com.example.zun;
import static com.example.zun.fakedb.NoDb.THEME_LIST;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zun.adapter.ThemeAdapter;
import com.example.zun.domain.Theme;
import com.example.zun.fragment.AddThemeFragment;
import com.example.zun.rest.ZunApi;
import com.example.zun.rest.ZunApiImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MSG_NAME = "themeFromListByPos";

    private AppCompatButton btnAdd;

    private FragmentTransaction transaction;

    private ThemeAdapter themeAdapter;

    private RecyclerView rvThemes;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;

    private final ZunApi zunApi = new ZunApiImpl(this);

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        zunApi.fillUser();
        zunApi.fillComment();
        zunApi.fillTheme();
        rvThemes = findViewById(R.id.rv_themes);
        themeAdapter= new ThemeAdapter(LayoutInflater.from(this), THEME_LIST, this);
        rvThemes.setAdapter(themeAdapter);


        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_main, new AddThemeFragment());
            transaction.commit();
        });

        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                Theme theme = THEME_LIST.get(position);

                if (swipeDir == ItemTouchHelper.LEFT) {
                    Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                    zunApi.deleteTheme(theme.getId());

                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvThemes);
    }
    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        if (size > 0)
            getSupportFragmentManager().beginTransaction().remove(fragments.get(size-1)).commit();
        else
            finish();
    }
    public void update() {

        themeAdapter.notifyDataSetChanged();
    }

}