package com.example.clublaribera_app.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clublaribera_app.R;


public class CerrarSesionFragment extends Fragment {
    View v;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // sendViewModel =
        //         ViewModelProviders.of(this).get(SendViewModel.class);
        View view = inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        v = view;
        notificacion();
        return view;
    }

    private void notificacion() {
        new AlertDialog.Builder(getContext()).setCancelable(false).setMessage("¿Desea salir de la aplicación?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Navigation.findNavController(v).navigate(R.id.nav_home, null);
            }
        }).show();
    }
}