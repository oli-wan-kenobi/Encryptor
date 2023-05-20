package com.AESEncryptor.support;

public class inputChecker {
    public String getType() {
        return type;
    }

    private String type;
    public inputChecker(String args[]) {
        System.out.println("Started AES user encryption program!");
        if(!"config".equals(args[0].substring(0, 6)))
        {
            System.out.println("Config file not found. Needs to be config.filetype");
            System.exit(0);
        }
        if(!"dataset".equals(args[1].substring(0, 7)))
        {
            System.out.println("Dataset file not found. Needs to be dataset.filetype");
            System.exit(0);
        }
        if(!"results".equals(args[2].substring(0, 7)))
        {
            System.out.println("Results name incorrect. Needs to be results.filetype");
            System.exit(0);
        }
        //Printing pretty letters :)
        System.out.println("Selected file " + args[0] + " as configuration file.");
        System.out.println("Selected file " + args[1] + " as dataset file.");
        System.out.println("Selected file " + args[2] + " as output file.");
        //Checking file extensions
        this.type = args[1].substring(args[1].length() - 4);
        System.out.println("Input file type is " + type +"!");
        //All 3 must be the same.
        if(((args[0].substring(args[0].length() - 4)).equals((args[1].substring(args[1].length() - 4))))
                && ((args[1].substring(args[1].length() - 4)).equals((args[2].substring(args[2].length() - 4 )))))
            System.out.println("File types match, so that's pretty OK.");
        else{
            System.out.println("File mismatch. Can't really accept that.");
            System.exit(0);
        }

    }
}
