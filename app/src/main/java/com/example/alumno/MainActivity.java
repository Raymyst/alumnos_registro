package com.example.alumno;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //crear mis clases para llamarlas previamente

    private EditText etCedula, etNombre, etApellido, etNota;
    private Button btnAgregar;
    private TextView tvResultados;
    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Conatruir los enlaces con los espacios para cumplir funsiones con la interfaz
        etCedula = findViewById(R.id.etCedula);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etNota = findViewById(R.id.etNota);
        btnAgregar = findViewById(R.id.btnAgregar);
        tvResultados = findViewById(R.id.tvResultados);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincular los string con os datos ingresados para imprimirlos en pantalla
                String cedula = etCedula.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String notaStr = etNota.getText().toString();

                if (cedula.length() != 10 || notaStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "La cédula debe tener 10 dígitos y la nota no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }
                //pongo condicionamientos para que la nota no pase de 10

                double nota = Double.parseDouble(notaStr);
                if (nota < 0 || nota > 10) {
                    Toast.makeText(MainActivity.this, "La nota debe estar entre 0 y 10", Toast.LENGTH_SHORT).show();
                    return;
                }
                //agrego un id automatico para los estudiantes ingresados

                Estudiante estudiante = new Estudiante(id, cedula, nombre, apellido, nota);
                listaEstudiantes.add(estudiante);
                id++;

                mostrarResultados();
                limpiarCampos();
            }
        });
    }

    private void mostrarResultados() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante estudiante : listaEstudiantes) {
            sb.append("ID: ").append(estudiante.getId()).append(", Cédula: ").append(estudiante.getCedula()).append(", Nombre: ").append(estudiante.getNombre()).append(" ").append(estudiante.getApellido()).append(", Nota: ").append(estudiante.getNota()).append("\n");
        }
        tvResultados.setText(sb.toString());
    }

    private void limpiarCampos() {
        etCedula.setText("");
        etNombre.setText("");
        etApellido.setText("");
        etNota.setText("");
    }

    class Estudiante {
        private int id;
        private String cedula;
        private String nombre;
        private String apellido;
        private double nota;
        //creo los getting y settings de los datos ingresados para imprimirlos en pantalla

        public Estudiante(int id, String cedula, String nombre, String apellido, double nota) {
            this.id = id;
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellido = apellido;
            this.nota = nota;
        }
        public int getId() {
            return id;
        }

        public String getCedula() {
            return cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public double getNota() {
            return nota;
        }
    }

}