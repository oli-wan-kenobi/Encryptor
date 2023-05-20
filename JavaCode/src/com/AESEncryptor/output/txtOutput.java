package com.AESEncryptor.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class txtOutput {


    public txtOutput(String[][] output, String outputFile) {

        int x = output.length;
        int y = output[0].length;
         /* Create some prints, stay bug free.
        System.out.println("\n\nThis is the encrypted (output) array in the output function");
        for(int i=0;i<x;i++) {
            System.out.print("\n");
            for (int j = 0; j < y; j++) {
                System.out.print(output[i][j] + "\t");
            }
        }*/


        //Check if file is there and empty, make sure it is.
        File file = new File(outputFile);
        try {
            if(file.exists()){
                System.out.println("File " + outputFile +  " exists! It will be overwritten!");
                file.delete();
                file.createNewFile();
            }
            else{
                System.out.println("File " + outputFile +  " doesn't exist! It will be created!");
                file.createNewFile();

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Finally write to the file.
        try {
            FileWriter eWriter = new FileWriter(outputFile);
            for(int i=0;i<x;i++) {
                for (int j = 0; j < y; j++) {
                    eWriter.write(output[i][j] + "\t");
                }
                eWriter.write('\n');
            }
            eWriter.close();
            System.out.println("Successfully wrote the encrypted data to " + outputFile + "!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }





}
