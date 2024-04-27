package android.me.b1_si_prak1a_13120220021;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText txt_edit_stb, txt_edit_nama;
    private TextView txt_nilai_akhir, txt_index;

    static final String KEY_STB = "STB";
    static final String KEY_NAMA = "NAMA";
    static final String KEY_NILAI_TUGAS = "NILAI_TUGAS";
    static final String KEY_NILAI_MID = "NILAI_MID";
    static final String KEY_NILAI_FINAL = "NILAI_FINAL";
    static final int RESULT_OK = 1;
    static final int RESULT_CANCEL = 0;
    private final int REQ_CODE_ACTIVITY2 = 0;

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

        txt_edit_stb = findViewById(R.id.txt_edit_stb);
        txt_edit_nama = findViewById(R.id.txt_edit_nama);
        txt_nilai_akhir = findViewById(R.id.txt_nilai_akhir);
        txt_index = findViewById(R.id.txt_index);

        txt_nilai_akhir.setText(":");
        txt_index.setText(":");
    }

    public void bukaActivity2(View view){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(KEY_STB, txt_edit_stb.getText().toString());
        intent.putExtra(KEY_NAMA, txt_edit_nama.getText().toString());
        txt_nilai_akhir.setText(":");
        txt_index.setText(":");
        startActivityForResult(intent, REQ_CODE_ACTIVITY2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){
            return;
        }

        if(requestCode == REQ_CODE_ACTIVITY2){
            if(resultCode == RESULT_OK){
                float nilaiAkhir, nTgs, nMid, nFinal;
                nTgs = Float.parseFloat(data.getStringExtra(KEY_NILAI_TUGAS));
                nMid = Float.parseFloat(data.getStringExtra(KEY_NILAI_MID));
                nFinal = Float.parseFloat(data.getStringExtra(KEY_NILAI_FINAL));

                nilaiAkhir = (nTgs + nMid + nFinal) / 3;
                txt_nilai_akhir.setText(": " + nilaiAkhir);

                char indeks = ' ';
                if (nilaiAkhir >= 90 && nilaiAkhir <= 100) indeks = 'A';
                else if (nilaiAkhir >= 80 && nilaiAkhir < 90) indeks = 'B';
                else if (nilaiAkhir >= 70 && nilaiAkhir < 80) indeks = 'C';
                else if (nilaiAkhir >= 45 && nilaiAkhir < 70) indeks = 'D';
                else if (nilaiAkhir < 45) indeks = 'E';

                txt_index.setText(": " + indeks);
            }
        }
        else if(resultCode == RESULT_CANCEL){
            txt_edit_stb.setText("");
            txt_edit_nama.setText("");
            txt_nilai_akhir.setText(":");
            txt_index.setText(":");
            Toast.makeText(this, "Input Nilai dibatalkan...", Toast.LENGTH_SHORT).show();
            txt_edit_stb.requestFocus();
        }
    }
}