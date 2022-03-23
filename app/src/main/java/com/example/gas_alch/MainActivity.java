package com.example.gas_alch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextGas;
    EditText editTextAlch;
    Button buttonCalc;
    TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
        bindFunctionalityToButtons();
    }

    protected void loadComponents() {
        editTextGas = findViewById(R.id.editTextGas);
        editTextAlch = findViewById(R.id.editTextAlch);
        buttonCalc = findViewById(R.id.buttonCalc);
        textViewResult = findViewById(R.id.textViewResult);
    }

    protected float getGasPrice() {
        String strGasPrice = editTextGas.getText().toString();
        Log.i("GASP:", strGasPrice);
        if (strGasPrice.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Entrada inválida")
                    .setMessage("O preço da gasolina está em formato inválido, digite novamente !")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            editTextGas.requestFocus();
            throw new IllegalArgumentException("gas price is empty");
        }
        return Float.parseFloat(strGasPrice);
    }

    protected float getAlcPrice() {
        String strAlcPrice = editTextAlch.getText().toString();
        Log.i("ALCP:", strAlcPrice);
        return Float.parseFloat(strAlcPrice);
    }

    protected void bindFunctionalityToButtons() {
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String result = calculateResult(
                            getGasPrice(),
                            getAlcPrice()
                    );
                    showResult(result);
                    clearEntries();
                } catch (Exception e) {
                    Log.e("[ERROR]:", e.toString());
                }
            }
        });
    }

    protected String calculateResult(float gasPrice, float alcPrice) {
        double maxGasPrice = gasPrice * 0.7;
        Log.i("maxPrice:", Double.toString(maxGasPrice));
        if (alcPrice <= maxGasPrice) {
            return "O álcool está compensando";
        }
        return "A gasolina é a melhor opção";
    }

    protected void showResult(String result) {
        textViewResult.setText(result);
    }

    protected void clearEntries() {
        editTextGas.setText("");
        editTextAlch.setText("");
    }

}