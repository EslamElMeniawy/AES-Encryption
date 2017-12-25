package elmeniawy.eslam.aesencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import elmeniawy.eslam.aesencryption.utils.AESHelper;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = findViewById(R.id.entered_text);
        Button bt = findViewById(R.id.encrypt);
        final TextView tv = findViewById(R.id.encrypted_text);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText() == null || et.getText().toString().isEmpty()) {
                    tv.setText("No text entered!");
                    return;
                }

                try {
                    String encryptedData = AESHelper.encrypt(getSecretKey(), et.getText().toString());
                    tv.setText(encryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                    tv.setText("Error!");
                }
            }
        });

        /*try {
            Log.e("1234567890abcdef", Arrays.toString("1234567890abcdef".getBytes(Charset.forName("UTF-8"))));
            String encryptedData = AESHelper.encrypt(getSecretKey(), "This should be encrypted.");
            Log.v("EncryptDecrypt", "Encoded String " + encryptedData);
            tv.append("\n\nEncoded String " + encryptedData);
            String decryptedData = AESHelper.decrypt(getSecretKey(), encryptedData);
            Log.v("EncryptDecrypt", "Decoded String " + decryptedData);
            tv.append("\n\nDecoded String " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String getSecretKey();
}
