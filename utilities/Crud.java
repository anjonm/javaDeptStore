package utilities;

import java.util.*;

public class Crud extends Buffered{
    General genUtil = new General();
    ArrayList<String> idOnly = new ArrayList<String>();
    ArrayList<String> reciept = new ArrayList<String>();
    private String data, categ, id, name;
    private int qty;
    private double price;

    public void setData(){
        this.data = String.format("id:%s#name:%s#price:%.2f#qty:%d", getId(), getName(), getPrice(), getQty());
    }

    private String getData(){
        return data;
    }

    public void setCateg(String categ){
        this.categ = categ;
    }

    private String getCateg(){
        return categ;
    }

    public void setId(String id){
        this.id = id;
    }

    private String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    private String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    private int getQty(){
        return qty;
    }

    public String addToCart(){
        for (String data : fileData) {
            if (data.contains(getId())) {
                return String.format("%s#buy:%d", data, getQty());
            }
        }
        return null;
    }

    public void addNew(){
        setData();
        fileData.add(getData());
        fileData.sort(null);
    }

    public void delete(){
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).contains(getId())) {
                fileData.remove(i);
            }
        }
    }

    public void addStocks() {
        String[] data;
        String lineData, returnData;
        int currentStocks;

        for (int dataIndex = 0; dataIndex < fileData.size(); dataIndex++) {
            lineData = fileData.get(dataIndex);
            if (lineData.contains(getId())) {
                data = lineData.split("#");
                for (int getQty = 0; getQty < data.length; getQty++) {
                    if (data[getQty].contains("qty:")) {
                        currentStocks = Integer.parseInt(data[getQty].substring(4));
                        currentStocks += getQty();
                        data[getQty] = "qty:" + currentStocks;
                    }
                }
                returnData = String.join("#", data);

                fileData.set(dataIndex, returnData);
            }
        }
    }

    public void checOut(ArrayList<String> reciept){
        HashMap<String, Integer> inventoryQtyMap = new HashMap<>();
        for (String line : fileData) {
            String[] parts = line.split("#");
            String id = parts[0].split(":")[1];
            int qty = Integer.parseInt(parts[3].split(":")[1]);
            inventoryQtyMap.put(id, qty);
        }

        for (String itemInReciept : reciept) {
            String[] parts = itemInReciept.split("#");
            String id = parts[0].split(":")[1];  // Extract ID
            int qtyInCart = Integer.parseInt(parts[2].split(":")[1]); // Extract QTYinCART

            if (inventoryQtyMap.containsKey(id)) {
                int newQty = inventoryQtyMap.get(id) - qtyInCart;
                inventoryQtyMap.put(id, newQty);
            }
        }
        for (int i = 0; i < fileData.size(); i++) {
            String[] parts = fileData.get(i).split("#");
            String id = parts[0].split(":")[1]; // Extract ID
            if (inventoryQtyMap.containsKey(id)) {
                int updatedQty = inventoryQtyMap.get(id);
                fileData.set(i, fileData.get(i).replaceFirst("qty:\\d+", "qty:" + updatedQty));
            }
        }
    }

    public void listReports(){
        System.out.println("--View Which Report--");
        System.out.print("[1] Inventory\n[2] Members\n[3] Sales\n[4] Suppliers\n[0] Back\n>");
    }

    public void view(){
        int title = 0;
        for (String infoData : fileData) {
            String[] contents = infoData.split("#");
            if (title == 0) { 
                for (String content : contents) {
                    System.out.print(content.split(":")[0] + " ");
                    title++;
                }
            }
            System.out.println();
            for (String content : contents) {
                System.out.print(content.split(":")[1] + " ");
            }
        }
        System.out.println();
    }

    public void view(String code){
        ArrayList<String> datas = new ArrayList<String>();
        int title = 0;
        for (String infoData : fileData) {
            if (infoData.contains(code)) {
                datas.add(infoData);
            }    
        }

        for (String data : datas) {
            String[] contents = data.split("#");
            if (title == 0) { 
                for (String content : contents) {
                    System.out.print(content.split(":")[0] + " ");
                    title++;
                }
            }
            System.out.println();
            for (String content : contents) {
                System.out.print(content.split(":")[1] + " ");
            }
        }
        System.out.println();
    }

    public ArrayList<String> view(ArrayList<String> itemInCart) {
        reciept.clear();
        HashMap<String, String> nameMap = new HashMap<>();
        HashMap<String, Double> priceMap = new HashMap<>();
        String[] parts;
        String id, name;
        double price, totalPrice;
        int title = 0, cartQty;

        for (String data : fileData) {
             parts = data.split("#");
             id = parts[0].split(":")[1];
             name = parts[1].split(":")[1];
             price = Double.parseDouble(parts[2].split(":")[1]);

            nameMap.put(id, name);
            priceMap.put(id, price);
        }

        for (String cartData : itemInCart) {
             parts = cartData.split("#");
             id = parts[0].split(":")[1];
             cartQty = Integer.parseInt(parts[1].split(":")[1]);

            if (nameMap.containsKey(id)) {
                 name = nameMap.get(id);
                 price = priceMap.get(id);
                 totalPrice = price * cartQty;

                reciept.add(String.format("ID:%s#NAME:%s#QTYinCART:%d#PRICE:%.2f", id, name, cartQty, totalPrice));
            }
        }

        System.out.println();
        totalPrice = 0;
        for (String itemInReciept : reciept) {
            String [] contents = itemInReciept.split("#");
            if (title == 0) {
                for (String content : contents) {
                    System.out.print(content.split(":")[0] + " ");
                    title++;
                }
            }
            System.out.println();
            for (String content : contents) {
                System.out.print(content.split(":")[1] + " ");
                if (content.contains("PRICE:")) {
                    totalPrice += Double.parseDouble(content.substring(6));
                }
            }
        }
        System.out.println();
        System.out.print("TOTAL PRICE: " + totalPrice);
        System.out.println();
        return reciept;
    }

    public ArrayList<String> viewBy(){
        ArrayList<String> availCode = new ArrayList<>();
        getAllProdId();
        for (String id : idOnly) {
            id = id.substring(0, 3);
            if (!availCode.contains(id)) {
                availCode.add(id);
            }
        }
        return availCode;
    }

    private void getAllProdId(){
        idOnly.clear();
        String[] infos;

        for (String data : fileData) {
            infos = data.split("#");
            for (String info : infos) {
                if (info.startsWith("id:")) {
                    idOnly.add(info.substring(3));
                }
            }
        }
    }

    private ArrayList<String> getIdByCateg(){
        ArrayList<String> idNum = new ArrayList<String>();
        for (String id : idOnly){
            if (id.startsWith(getCateg())){
                idNum.add(id);
            }
        }
        return idNum;
    }

    public Boolean verifyId(){
        if (getCateg() != "all"){
            for (String id : getIdByCateg()){
                if (id.contains(getId())) {
                    return true;
                }
            }
        } else {
            for (String id : idOnly){
                if (id.contains(getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getLatestId(){
            String[] infos;
            int idLength, latestIdCnt;
            String latestId;
            ArrayList<String> idNum = new ArrayList<String>();
            // getting the id of all the product
            getAllProdId();
            
            // separating the id base on the needed product
            idNum = getIdByCateg();
            for (String id : idOnly){
                if (id.startsWith(getCateg())){
                    idNum.add(id);
                }
            }
            // generating new productId
            if (idNum.size() == 0) {
                // no product exist yet with the same code
                return getCateg() + "000000";
            } else {
                // product already exist
                // getting the last product
                latestId = idNum.get(idNum.size()-1);
                idLength = latestId.length();
                latestId = latestId.substring(3, idLength);
                latestIdCnt = Integer.parseInt(latestId) + 1;
                
                idLength = String.valueOf(latestIdCnt).length();
                idLength = latestId.length() - idLength;
                infos = new String[idLength];
                for (int cnt = 0; cnt < idLength; cnt++){
                    infos[cnt] = "0";
                }
                latestId = getCateg() + String.join("", infos) + latestIdCnt;
                
            }
            return latestId;
        }
}
/*
INVENTORY
MEMBERS
SALES
SUPPLIERS
*/ 