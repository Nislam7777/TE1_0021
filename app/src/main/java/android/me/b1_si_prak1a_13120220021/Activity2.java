package android.me.b1_si_prak1a_13120220021;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    private TextView txt_stb, txt_nama;
    private EditText txt_input_nilai_tugas, txt_input_nilai_mid, txt_input_nilai_final;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_stb = findViewById(R.id.txt_stb);
        txt_nama = findViewById(R.id.txt_nama);
        txt_input_nilai_tugas = findViewById(R.id.txt_input_nilai_tugas);
        txt_input_nilai_mid = findViewById(R.id.txt_input_nilai_mid);
        txt_input_nilai_final = findViewById(R.id.txt_input_nilai_final);

        Intent intent = getIntent();
        String stb, nama;

        stb = intent.getStringExtra(MainActivity.KEY_STB);
        nama = intent.getStringExtra(MainActivity.KEY_NAMA);

        txt_stb.setText(stb);
        txt_nama.setText(nama);
    }

    public void inputSelesai(View view){
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_NILAI_TUGAS, txt_input_nilai_tugas.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_MID, txt_input_nilai_mid.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_FINAL, txt_input_nilai_final.getText().toString());
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

    public void inputBatal(View view){
        Intent intent = new Intent();
        setResult(MainActivity.RESULT_CANCEL, intent);
        finish();
    }
}