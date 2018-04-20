package eecs1022.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab6Activity extends AppCompatActivity
{
    private Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
    }

    private void setTextViewById(int ID, String text) {
        TextView view = (TextView) findViewById(ID);
        view.setText(text);
    }

    private String getInputById(int ID) {
        EditText view = (EditText) findViewById(ID);
        return view.getText().toString();
    }

    private String getItemSelectedById(int ID) {
        Spinner spinner = (Spinner) findViewById(ID);
        return spinner.getSelectedItem().toString();
    }

    public void onCreateGame(View v) {
        String o = getInputById(R.id.inputNameo);
        String x = getInputById(R.id.inputNamex);
        String first = getItemSelectedById(R.id.spinnerWho);

        g = new Game(o, x, first.equals("Player X"));

        setTextViewById(R.id.labelResult, g.toString());
    }

    public void onPlayGame(View v) {
        int row = Integer.parseInt(getItemSelectedById(R.id.spinnerRow)) - 1;
        int col = Integer.parseInt(getItemSelectedById(R.id.spinnerColumn)) - 1;

        setTextViewById(R.id.labelResult, g.place(row, col));
    }
}
