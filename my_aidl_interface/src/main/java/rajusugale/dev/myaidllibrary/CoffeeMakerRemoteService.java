package rajusugale.dev.myaidllibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class CoffeeMakerRemoteService extends Service {

    List<Ingredient> ingredients=new ArrayList<>();
    public CoffeeMakerRemoteService(){
        ingredients=new ArrayList<>();
    }

    private void log(String message) {
        Log.v("RemoteService", message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Received start command.");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return mBinder;
    }

    private final ICoffeeMakerRemoteService.Stub mBinder = new ICoffeeMakerRemoteService.Stub() {
        @Override
        public void addIngredient(String name, int quantity, String ingreType) throws RemoteException {
            Ingredient ingredient=new Ingredient(name, quantity, ingreType);
            ingredients.add(ingredient);
        }

        @Override
        public Ingredient getIngredient(String name) throws RemoteException {
            for (int i = 0; i < ingredients.size(); i++) {
                if(ingredients.get(i).getName().equals(name)){
                    return ingredients.get(i);
                }
            }
            return null ;
        }
    };
}

