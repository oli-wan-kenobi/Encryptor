package com.AESEncryptor.support;

import com.AESEncryptor.encryption.AES;
import com.AESEncryptor.output.csvOutput;
import com.AESEncryptor.output.jsonOutput;
import com.AESEncryptor.output.txtOutput;
import com.AESEncryptor.output.xmlOutput;
import com.AESEncryptor.parsers.csvParse;
import com.AESEncryptor.parsers.jsonParse;
import com.AESEncryptor.parsers.txtParse;
import com.AESEncryptor.parsers.xmlParse;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class switcher {
    public switcher(String args[], String type) {
        //A switch, much prettier than if statements I guess. Creating instance of the proper class.
        //Only txtParser implemented for now. Creating the instances as a guideline to future developers.
        switch (type) {
            case ".txt":
                System.out.println("Starting txt parser.");
                txtParse parser = new txtParse(args);
                //calling encryption
                AES encryptor;
                try {
                    encryptor = new AES(parser.getTextArray(), parser.getHitArray());
                    String[][] encryptedTextArray = encryptor.getEncryptedTextArray();
                    System.out.println("\n\nEncryption successful! Moving on to creating output file.");
                    System.out.println("Starting txt file output.");
                    txtOutput out0 = new txtOutput(encryptedTextArray, args[2]);
                }
                catch (NoSuchAlgorithmException e){
                    System.out.println("Error! No such algorithm!");
                }
                catch (NoSuchPaddingException e){
                    System.out.println("Error! No such Padding");
                }
                catch (IllegalBlockSizeException e){
                    System.out.println("Error! No such Padding");
                }
                catch (InvalidAlgorithmParameterException e){
                    System.out.println("Error!");
                }
                catch (InvalidKeyException e){
                    System.out.println("Error!");
                }
                catch (BadPaddingException e){
                    System.out.println("Error!");
                }

                break;
            case ".csv":
                System.out.println("Starting csv parser.");
                csvParse parser1 = new csvParse();
                //call encryption here
                System.out.println("Starting csv file output.");
                csvOutput out1 = new csvOutput();
                break;
            case ".xml":
                System.out.println("Starting xml parser.");
                xmlParse parser2 = new xmlParse();
                //call encryption here
                System.out.println("Starting xml file output.");
                xmlOutput out2 = new xmlOutput();
                break;
            case "json":
                System.out.println("Starting json parser.");
                jsonParse parser3 = new jsonParse();
                //call encryption here
                System.out.println("Starting json file output.");
                jsonOutput out3 = new jsonOutput();
                break;
            default:
                System.out.println("Starting " + type + " parser. Wait what?");
                System.exit(0);
                break;
        }
    }
}
