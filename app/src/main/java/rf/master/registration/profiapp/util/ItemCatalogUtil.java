package rf.master.registration.profiapp.util;

import rf.master.registration.profiapp.data.entity.ItemCatalogNames;

/**
 * Created by Shiplayer on 08.04.18.
 */

public class ItemCatalogUtil {
    private static final String TAG = ItemCatalogUtil.class.getSimpleName();
    public static final String[] listNames = {"Парикхмахер", "Барбишопер", "Маникюр", "Педикюр", "Визажист", "Покраска волос", "Детская стрижка"};

    public static String[] getItemCatalogNames(String keyWord){
        ItemCatalogNames itemCatalogNames = new ItemCatalogNames();
        if(keyWord.equals("Мужчины")){
            String[] buf = new String[2];
            buf[0] = listNames[0];
            buf[1] = listNames[1];
            return buf;
        } else
            return listNames;

    }

}
