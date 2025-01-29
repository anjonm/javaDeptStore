package utilities;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.util.*;

class Buffered {
    protected ArrayList<String> fileData = new ArrayList<String>();

    
    // private String prodPath = "savedFiles/products.txt";
    // private String suppPath = "savedFiles/suppliers.txt";
    // private String toCart = "savedFiles/cart.txt";

    // public void setPath(String path){
    //     if (path == "product") {
    //         this.path = prodPath;
    //     } else if (path == "supplier") {
    //         this.path = suppPath;
    //     } else if (path == "toCart") {
    //         this.path = toCart;
    //     }
    // }

    // private String getPath(){
    //     return path;
    // }

    public void retrieveData(String path){
        fileData.clear();
        Path file = Paths.get(path);
        String lines;

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            while ((lines = reader.readLine()) != null) {
                fileData.add(lines);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void updateFile(String path){
        Path file = Paths.get(path);
        OutputStream o = null;
        try {
            o = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(o));
            for (String data : fileData) {
                writer.write(data);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFile(){ 
        File myObj = new File("savedFiles/products.txt"); 
        myObj.delete();
        // if (myObj.delete()) { 
        // System.out.println("Deleted the file: " + myObj.getName());
        // } else {
        // System.out.println("Failed to delete the file.");
        // } 
    } 
}
