package elmeniawy.eslam.aesencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(getSecretKey());

        try {
            Log.e("1234567890abcdef", Arrays.toString("1234567890abcdef".getBytes(Charset.forName("UTF-8"))));
            String encryptedData = AESHelper.encrypt(getSecretKey(), "This should be encrypted.");
            Log.v("EncryptDecrypt", "Encoded String " + encryptedData);
            tv.append("\n\nEncoded String " + encryptedData);
            String decryptedData = AESHelper.decrypt(getSecretKey(), encryptedData);
            Log.v("EncryptDecrypt", "Decoded String " + decryptedData);
            tv.append("\n\nDecoded String " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String getSecretKey();
}
