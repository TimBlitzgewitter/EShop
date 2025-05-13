package domain;

import CUI.ShopCUI;
import persistenz.FilePersistenzManager;

public class EShop {

    

    public static void main(String[] args) {
            FilePersistenzManager filePersistenzManager = new FilePersistenzManager();
            filePersistenzManager.erzeugeTestlisten();
            ShopCUI cui = new ShopCUI();
            cui.run();
    }


}