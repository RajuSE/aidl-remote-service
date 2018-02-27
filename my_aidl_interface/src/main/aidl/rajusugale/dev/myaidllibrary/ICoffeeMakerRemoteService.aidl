// IRemoteProductService.aidl
package rajusugale.dev.myaidllibrary;

import rajusugale.dev.myaidllibrary.Ingredient;

interface ICoffeeMakerRemoteService {

//    void addIngredient(Ingredient ingredient);//Dont Do- Gives Error S-41461954
    void addIngredient(String name, int quantity, String ingreType);
    Ingredient getIngredient(String name);
}
