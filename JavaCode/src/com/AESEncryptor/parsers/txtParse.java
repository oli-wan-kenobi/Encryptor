package com.AESEncryptor.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class txtParse {
    //private variables for processing text file and converting into array.
    private String[][] textArray;
    private String[] configArray;
    private int dataLines;
    private int configLines;
    private int dataColumns;
    private boolean[] hitArray;

    //one beefy constructor
    public txtParse(String[] input){
            this.dataLines=0;
            this.configLines=0;
            this.dataColumns=0;
            //opening dataset. Counting lines to create textArray later.
           File file1 = new File(input[1]);
           this.dataLines = 0;
           try{
            Scanner s = new Scanner(file1);
            while (s.hasNextLine()) {
                this.dataLines++;
                s.nextLine();
            }
            s.close();
           }
           catch(FileNotFoundException ex) {
               System.out.println("File doesn't exist! Can't let you do that!");
               System.exit(0);

           }
           //same for words in the first line. need to know how many fields my data has.
        try{
            Scanner s = new Scanner(file1);
            String input1=s.nextLine();
            String[] words = input1.split("\\t+");
            this.dataColumns=words.length;
            s.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File doesn't exist! Can't let you do that!");
            System.exit(0);

        }
        //counting lines for config file. That's all we need.
        File file0 = new File(input[0]);
        this.configLines = 0;
        try{
            Scanner s = new Scanner(file0);
            while (s.hasNextLine()) {
                this.configLines++;
                s.nextLine();
            }
            s.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File doesn't exist! Can't let you do that!");
            System.exit(0);

        }
        //Checking
        System.out.println("Data file is " + dataLines + " lines long and " + dataColumns + " columns wide.");
        System.out.println("Config file is " + configLines + " lines long.");

        //Creating config array to compare to textArray later.
        configArray = new String[configLines];
        try {
            Scanner s = new Scanner(file0);
            for (int i = 0; i < configLines; i++) {
                configArray[i]=s.nextLine();
            }
            s.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File doesn't exist! Can't let you do that!");
            System.exit(0);
        }
        //Creating text array
        textArray=new String[dataLines][dataColumns];
        try{
            Scanner s = new Scanner(file1);
            for(int i=0;i<dataLines;i++)
                for(int j=0;j<dataColumns;j++)
                    textArray[i][j]=s.next();

            s.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File doesn't exist! Can't let you do that!");
            System.exit(0);

        }
        /* test printing
        for(int i=0;i<dataLines;i++) {
            System.out.println(" ");
            for (int j = 0; j < dataColumns; j++)
                System.out.print(i + ":" + j + " " + textArray[i][j] + " ");
        }
            */
        //creating hitArray. Essentially a hit is one value present in both the config and data file (row 0 of course unless someone's name is afm?) Each value of "1" in this array
        // represents a column that will be encrypted.
        hitArray=new boolean[dataColumns];
            for(int j=0;j<dataColumns;j++) {
                hitArray[j]=false;
                for (int l = 0; l < configLines; l++) {
                    if (textArray[0][j].equals(configArray[l])) {
                        System.out.println("Hit in column " + j + ", " + textArray[0][j] +" will be encrypted.");
                        hitArray[j] = true;
                    }
                }
            }
    }

//these will be invoked by the AES constructor
    public String[][] getTextArray() {
        return textArray;
    }


    public boolean[] getHitArray() {
        return hitArray;
    }
}

