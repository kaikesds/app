package com.example.kaike;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etNome, etEmail, etIdade, etDisciplina, etNota1, etNota2;
    private TextView tvResultado;
    private Button btnEnviar, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etIdade = findViewById(R.id.etIdade);
        etDisciplina = findViewById(R.id.etDiciplina);
        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        tvResultado = findViewById(R.id.tvResultado);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnReset = findViewById(R.id.btnReset);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDados();
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetarFormulario();
            }
        });
    }

    private void validarDados() {

        String nome = etNome.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String idadeStr = etIdade.getText().toString().trim();
        String disciplina = etDisciplina.getText().toString().trim();
        String nota1Str = etNota1.getText().toString().trim();
        String nota2Str = etNota2.getText().toString().trim();


        if (TextUtils.isEmpty(nome)) {
            Toast.makeText(this, "O campo de nome está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "O email é inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(idadeStr) || !isPositiveNumber(idadeStr)) {
            Toast.makeText(this, "A idade deve ser um número positivo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(disciplina)) {
            Toast.makeText(this, "O campo de disciplina está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidNota(nota1Str)) {
            Toast.makeText(this, "A nota do 1º bimestre deve ser entre 0 e 10", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidNota(nota2Str)) {
            Toast.makeText(this, "A nota do 2º bimestre deve ser entre 0 e 10", Toast.LENGTH_SHORT).show();
            return;
        }


        int idade = Integer.parseInt(idadeStr);
        double nota1 = Double.parseDouble(nota1Str);
        double nota2 = Double.parseDouble(nota2Str);
        double media = (nota1 + nota2) / 2;

        String resultadoAprovacao = media >= 6 ? "Aprovado" : "Reprovado";


        String resultado = "Nome: " + nome + "\nEmail: " + email + "\nIdade: " + idade
                + "\nDisciplina: " + disciplina + "\nNota 1º Bimestre: " + nota1
                + "\nNota 2º Bimestre: " + nota2 + "\nMédia: " + media
                + "\nResultado: " + resultadoAprovacao;

        tvResultado.setText(resultado);
        tvResultado.append(resultado + "\n\n");
        tvResultado.setTextColor(getResources().getColor(android.R.color.black));
    }

    private boolean isPositiveNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidNota(String str) {
        try {
            double nota = Double.parseDouble(str);
            return nota >= 0 && nota <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void resetarFormulario() {

        etNome.setText("");
        etEmail.setText("");
        etIdade.setText("");
        etDisciplina.setText("");
        etNota1.setText("");
        etNota2.setText("");
        tvResultado.setText("");
    }

}