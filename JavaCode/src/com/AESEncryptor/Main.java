package com.AESEncryptor;

import com.AESEncryptor.support.inputChecker;
import com.AESEncryptor.support.switcher;

public class Main {

    public static void main(String[] args) {

        //Check if the input files are the correct name. Should be config, dataset and results for the output.
        inputChecker myHelper = new inputChecker(args);
        //Do the switch within its own class.
        switcher mySwitcher = new switcher(args, myHelper.getType());



    }

}
