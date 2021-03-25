package pollub.ism.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int click = 1;
    char[][] ticTacToe =
            {
                    {' ',' ',' '},
                    {' ',' ',' '},
                    {' ',' ',' '}
            };
    public char checkWin(){
        if (ticTacToe[0][0] == ticTacToe[0][1] && ticTacToe[0][0] == ticTacToe[0][2] && ticTacToe[0][0] != ' ')
        {
            return ticTacToe[0][0];
        }
        if (ticTacToe[1][0] == ticTacToe[1][1] && ticTacToe[1][0] == ticTacToe[1][2] && ticTacToe[1][0] != ' ')
        {
            return ticTacToe[1][0];
        }
        if (ticTacToe[2][0] == ticTacToe[2][1] && ticTacToe[2][0] == ticTacToe[2][2] && ticTacToe[2][0] != ' ')
        {
            return ticTacToe[2][0];
        }
        if (ticTacToe[0][0] == ticTacToe[1][0] && ticTacToe[1][0] == ticTacToe[2][0] && ticTacToe[0][0] != ' ')
        {
            return ticTacToe[0][0];
        }
        if (ticTacToe[0][1] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][1] && ticTacToe[0][1] != ' ')
        {
            return ticTacToe[0][1];
        }
        if (ticTacToe[0][2] == ticTacToe[1][2] && ticTacToe[1][2] == ticTacToe[2][2] && ticTacToe[0][2] != ' ')
        {
            return ticTacToe[0][2];
        }
        if (ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][2] && ticTacToe[0][0] != ' ')
        {
            return ticTacToe[0][0];
        }
        if (ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[1][1] == ticTacToe[2][0] && ticTacToe[0][2] != ' ')
        {
            return ticTacToe[0][2];
        }
        else return 0;

    }
    public void kliknieciePrzycisku(View view){
        String buttonName = view.getResources().getResourceEntryName(view.getId());
        Button btn = (Button) findViewById(view.getId());
        int a = buttonName.charAt(7)-48;
        int b = buttonName.charAt(9)-48;
        if (click%2==1 && ticTacToe[a][b] == ' ' && click !=10)
        {
            btn.setText("O");
            click++;
            ticTacToe[a][b] = 'O';
        }
        else if(ticTacToe[a][b] == ' ' && click !=10)
        {
            btn.setText("X");
            click++;
            ticTacToe[a][b] = 'X';
        }
        if (click>3 && click !=11)
        {
            char result = checkWin();
            if (result=='X')
            {
                Toast.makeText(this,"Wygrały X", Toast.LENGTH_LONG).show();
                for (int i=0; i<3;i++)
                {
                    for (int j = 0; j<3; j++)
                    {
                        ticTacToe[i][j] = ' ';
                    }
                }
                Intent newGame = new Intent(this, MainActivity.class);
                startActivity(newGame);
            }
            if (result=='O')
            {
                Toast.makeText(this,"Wygrały O", Toast.LENGTH_LONG).show();
                for (int i=0; i<3;i++)
                {
                    for (int j = 0; j<3; j++)
                    {
                        ticTacToe[i][j] = ' ';
                    }
                }
                Intent newGame = new Intent(this, MainActivity.class);
                startActivity(newGame);
            }
            else if (click==10)
            {
                Toast.makeText(this,"REMIS", Toast.LENGTH_LONG).show();
                for (int i=0; i<3;i++)
                {
                    for (int j = 0; j<3; j++)
                    {
                        ticTacToe[i][j] = ' ';
                    }
                }
                Intent newGame = new Intent(this, MainActivity.class);
                startActivity(newGame);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button = (Button) findViewById(getResources().getIdentifier("button_" + (i) +"_"+ (j), "id", this.getPackageName()));

                button.setText(Character.toString(ticTacToe[i][j]));
            }
        }
    }
    private static String KEY_CLICK = "Wartosc licznika click";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CLICK, click);
        outState.putSerializable("test",ticTacToe);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        click = savedInstanceState.getInt(KEY_CLICK, 0);
        ticTacToe = (char[][]) savedInstanceState.getSerializable("test");
    }
}