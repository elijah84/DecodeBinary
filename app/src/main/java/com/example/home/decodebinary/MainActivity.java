package com.example.home.decodebinary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // Declare variables
    TextView tvBinary, tvTrack1, tvTrack2;



    // Construct simulated card swipe raw binary data
    String preamble = "0000000000000010";
    String track1 = "%B4815821033808532^ASSEFA/BELAYE^2105201000000000816100161000000?";
    String track2 = ";4815821033808532=21052010000000008161?";
    String track1Bin =   "1010001" +   //"%"
                         "0100011" +   //"B"
                         "0010101" +   //"4"
                         "0001101" +   //"8"
                         "1000101" +   //"1"
                         "1010100" +   //"5"
                         "0001101" +   //"8"
                         "0100101" +   //"2"
                         "1000101" +   //"1"
                         "0000100" +   //"0"
                         "1100100" +   //"3"
                         "1100100" +   //"3"
                         "0001101" +   //"8"
                         "0000100" +   //"0"
                         "0001101" +   //"8"
                         "1010100" +   //"5"
                         "1100100" +   //"3"
                         "0100101" +   //"2"
                         "0111110" +   //"ˆ"
                         "1000011" +   //"A"
                         "1100111" +   //"S"
                         "1100111" +   //"S"
                         "1010010" +   //"E"
                         "0110010" +   //"F"
                         "1000011" +   //"A"
                         "1111001" +   //"/"
                         "0100011" +   //"B"
                         "1010010" +   //"E"
                         "0011010" +   //"L"
                         "1000011" +   //"A"
                         "1001111" +   //"Y"
                         "1010010" +   //"E"
                         "0111110" +   //"ˆ"
                         "0100101" +   //"2"
                         "1000101" +   //"1"
                         "0000100" +   //"0"
                         "1010100" +   //"5"
                         "0100101" +   //"2"
                         "0000100" +   //"0"
                         "1000101" +   //"1"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0001101" +   //"8"
                         "1000101" +   //"1"
                         "0110100" +   //"6"
                         "1000101" +   //"1"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "1000101" +   //"1"
                         "0110100" +   //"6"
                         "1000101" +   //"1"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "0000100" +   //"0"
                         "1111100";    //"?"
    String track1LRC =   "0111011";
    String track2Bin =   "11010" +   //";"
                         "00100" +   //"4"
                         "00010" +   //"8"
                         "10000" +   //"1"
                         "10101" +   //"5"
                         "00010" +   //"8"
                         "01000" +   //"2"
                         "10000" +   //"1"
                         "00001" +   //"0"
                         "11001" +   //"3"
                         "11001" +   //"3"
                         "00010" +   //"8"
                         "00001" +   //"0"
                         "00010" +   //"8"
                         "10101" +   //"5"
                         "11001" +   //"3"
                         "01000" +   //"2"
                         "10110" +   //"="
                         "01000" +   //"2"
                         "10000" +   //"1"
                         "00001" +   //"0"
                         "10101" +   //"5"
                         "01000" +   //"2"
                         "00001" +   //"0"
                         "10000" +   //"1"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00001" +   //"0"
                         "00010" +   //"8"
                         "10000" +   //"1"
                         "01101" +   //"6"
                         "10000" +   //"1"
                         "11111";    //"?"
    String track2LRC =   "11001";
    String track1Zeros = String.format("%0" + (608 - track1Bin.length() - track1LRC.length())/2 + "d", 0);
    String track2Zeros = String.format("%0" + (608 - track2Bin.length() - track2LRC.length())/2 + "d", 0);
    String track1wZeros = track1Zeros + track1Bin + track1Zeros;
    String track2wZeros = track2Zeros + track2Bin + track2Zeros;
    String strBinary = preamble + track1wZeros + track2wZeros;     // Total simulated card raw binary


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set variable values
        tvBinary = (TextView)findViewById(R.id.tvBinary);
        tvTrack1 = (TextView)findViewById(R.id.tvTrack1);
        tvTrack2 = (TextView)findViewById(R.id.tvTrack2);


        Button btnCard = (Button) findViewById(R.id.btnCard);
        btnCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                tvBinary.setText(strBinary);

                // Filter preamble which is: 0000 0000 0000 0010 from strBinary
                String strBinaryNoPream = strBinary.substring(16);

                // Split strBinary into track1 and track2
                String strTrack1wZeros = strBinaryNoPream.substring(0,608);
                String strTrack2wZeros = strBinaryNoPream.substring(608);

                // Filter leading and trailing zeros from strTrack1 and strTrack2
                // track1 start sentinel: 1010001
                // track1 end sentinel: 1111100
                int track1StartIndex = strTrack1wZeros.indexOf("1010001");
                int track1EndIndex = strTrack1wZeros.lastIndexOf("1111100");

                // track2 start sentinel: 11010
                // track2 end sentinel: 11111
                int track2StartIndex = strTrack2wZeros.indexOf("11010");
                int track2EndIndex = strTrack2wZeros.lastIndexOf("11111");

                String strTrack1 = strTrack1wZeros.substring(track1StartIndex,track1EndIndex+7);
                String strTrack2 = strTrack2wZeros.substring(track2StartIndex,track2EndIndex+5);

                // Number of characters in track 1 and 2
                int numTrack1 = strTrack1.length()/7; // max is 79
                int numTrack2 = strTrack2.length()/5; // max is 40

                // Declare and initialize arrays
                String[] arrayTrack1Bin = new String[numTrack1];
                String[] arrayTrack2Bin = new String[numTrack2];
                int[] arrayTrack1Dec = new int[numTrack1];
                int[] arrayTrack2Dec = new int[numTrack2];
                char[] arrayTrack1Char = new char[numTrack1];
                char[] arrayTrack2Char = new char[numTrack2];

                for (int i = 0; i < numTrack1; i++){
                    arrayTrack1Bin[i] = strTrack1.substring(i*7,(i+1)*7);
                    arrayTrack1Bin[i] = new StringBuilder(arrayTrack1Bin[i].substring(0,6)).reverse().toString();
                    arrayTrack1Dec[i] = Integer.parseInt(arrayTrack1Bin[i], 2) + 32;
                    arrayTrack1Char[i] = (char)arrayTrack1Dec[i];
                }

                for (int i = 0; i < numTrack2; i++){
                    arrayTrack2Bin[i] = strTrack2.substring(i*5,(i+1)*5);
                    arrayTrack2Bin[i] = new StringBuilder(arrayTrack2Bin[i].substring(0,4)).reverse().toString();
                    arrayTrack2Dec[i] = Integer.parseInt(arrayTrack2Bin[i], 2) + 48;
                    arrayTrack2Char[i] = (char)arrayTrack2Dec[i];
                }

                tvTrack1.setText(String.valueOf(arrayTrack1Char));
                tvTrack2.setText(String.valueOf(arrayTrack2Char));

            }
        });
    }
}
