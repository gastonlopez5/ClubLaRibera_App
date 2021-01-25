package com.example.clublaribera_app.ui.registro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clublaribera_app.R;
import com.example.clublaribera_app.modelos.TipoUsuario;
import com.example.clublaribera_app.modelos.Usuario;
import com.example.clublaribera_app.ui.login.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    private RegistroViewModel vm;
    private Spinner tipoUsuario;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etTelefono;
    private EditText etRepetirClave;
    private EditText etEmail;
    private EditText etClave;
    private ImageButton btFoto;
    private Button btRegistro;
    private ImageView ivFoto;
    private Bitmap bitmapFoto = null;
    private Boolean bandera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(RegistroViewModel.class);

        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellindo);
        etClave = findViewById(R.id.et_password);
        etRepetirClave = findViewById(R.id.et_confirm_password);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        tipoUsuario = findViewById(R.id.spTipoUsuario);
        btFoto = findViewById(R.id.btFoto);
        btRegistro = findViewById(R.id.button_signin);
        ivFoto = findViewById(R.id.ivFoto);

        cargaSpinner();

        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String u) {
                Toast.makeText(getApplicationContext(), u, Toast.LENGTH_LONG).show();
                bandera = false;
            }
        });

        vm.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                bitmapFoto = bitmap;
                ivFoto.setImageBitmap(bitmap);
            }
        });

        btFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent, 10);
            }
        });

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setNombre(etNombre.getText().toString());
                u.setApellido(etApellido.getText().toString());
                u.setClave(etClave.getText().toString());
                u.setEmail(etEmail.getText().toString());
                u.setTelefono(etTelefono.getText().toString());
                u.setTipoUsuario((TipoUsuario) tipoUsuario.getSelectedItem());

                vm.registrarUsuario(u, bitmapFoto, etRepetirClave.getText().toString());

                if (bandera){
                    Intent logeo = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(logeo);
                }
                else {
                    fijarDatos(u, bitmapFoto);
                    bandera = true;
                }
            }
        });
    }

    private void fijarDatos(Usuario u, Bitmap bitmap){
        etNombre.setText(u.getNombre());
        etApellido.setText(u.getApellido());
        etEmail.setText(u.getEmail());
        etTelefono.setText(u.getTelefono());
        tipoUsuario.setSelection(u.getTipoUsuario().getId());
        ivFoto.setImageBitmap(bitmap);
    }

    private void cargaSpinner(){
        ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();
        lista.add(new TipoUsuario(0, "Elije tu rol dentro de la institución"));
        lista.add(new TipoUsuario(2, "Padre o Tutor"));
        lista.add(new TipoUsuario(3, "Profesor"));
        lista.add(new TipoUsuario(4, "Fan"));

        ArrayAdapter<TipoUsuario> adapter = new ArrayAdapter<TipoUsuario>(this, R.layout.support_simple_spinner_dropdown_item, lista);
        tipoUsuario.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vm.cargarImagen(requestCode,resultCode,data);
    }


}