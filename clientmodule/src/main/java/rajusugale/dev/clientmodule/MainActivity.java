package rajusugale.dev.clientmodule;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rajusugale.dev.myaidllibrary.ICoffeeMakerRemoteService;

public class MainActivity extends AppCompatActivity {

    private ICoffeeMakerRemoteService iCoffeeMakerRemoteService;
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = (TextView) findViewById(R.id.log);

        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "rajusugale.dev.myaidllibrary",
                        "rajusugale.dev.myaidllibrary.CoffeeMakerRemoteService"));
        mLog.setText("Starting service…\n");
//        startService(serviceIntent);
        mLog.append("Binding service…\n");
        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mLog.append("Service binded!\n");
            iCoffeeMakerRemoteService = ICoffeeMakerRemoteService.Stub.asInterface(service);

            addIngredients();

            searchCaffein();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            iCoffeeMakerRemoteService = null;
            // This method is only invoked when the service quits from the other end or gets killed
            // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
            mLog.append("Service disconnected.\n");
        }
    };

    private void addIngredients() {
        mLog.append("Adding Ingredients…\n");
        long start = System.currentTimeMillis();
        long end = 0;
        try {
            iCoffeeMakerRemoteService.addIngredient("Caffeine", 2, "Liquid");
            iCoffeeMakerRemoteService.addIngredient("Water", 6, "Liquid");
            iCoffeeMakerRemoteService.addIngredient("Acetylmethylcarbinol", 3, "Powder");

            end = System.currentTimeMillis();
            mLog.append("3 Ingredients added…\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        try {
//            mService.exit();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }


    void searchCaffein() {
        long start = System.currentTimeMillis();
        long end = 0;
        try {
            mLog.append("\n Searching -->\"Caffeine\" \n FOUND :: " + iCoffeeMakerRemoteService.getIngredient("Caffeine") + "\n");
            end = System.currentTimeMillis();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mLog.append("Operation took " + (((double) end - (double) start) / 1000d) + " seconds\n");
    }
}
