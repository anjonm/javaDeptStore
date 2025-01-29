package utilities;

import java.util.ArrayList;

public class General {
    String[] categories = {"clothing", "cosmetics", "furniture", "gadgets", "gardening", "hardware", "home appliances", "houseware", "paint", "sporting goods", "toiletries", "toys"};
    
    private ArrayList<String> toGetCateg;

    public void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void setCategory(ArrayList<String> categs){
        this.toGetCateg = categs;
    }

    public ArrayList<String> getCategory(){
        ArrayList<String> availCateg = new ArrayList<String>();
        if (this.toGetCateg == null) {
            for (int categoryIndex = 0; categoryIndex < categories.length; categoryIndex++) {
                System.out.println(String.format("[%d] %s", categoryIndex + 1, categories[categoryIndex]));
                availCateg.add(categories[categoryIndex]);
            }
            System.out.print("[0] Back\n>");
        } else {
            int optionCount = 1;
            for (String needCateg : toGetCateg) {
                for (String category : categories)
                if (category.startsWith(needCateg.toLowerCase())){
                    availCateg.add(category);
                    System.out.println(String.format("[%d] %s", optionCount++, category));
                }
            }
            System.out.print(String.format("[%d] VIEWALL\n[0] Back\n>", optionCount));
        }
        return availCateg;
    }

    public String categoryIs(int index){
        return categories[index - 1];
    }

    public int categoryLength(){
        return categories.length;
    }
}
