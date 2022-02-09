/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.camelwriter;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erso
 */
public class CamelWriter {

    private File inFile;


    public CamelWriter(String fName) {
        //Initialiser inFile med fName
        inFile = new File(fName);
        if(!inFile.getName().contains(".txt")){
            inFile = new File(fName + ".txt");
        }


    }

    public void readLines() {
        // Benyt en Scanner til at læse inFile én linje ad gangen
        // Kald convert2camel med hver linje.
        Scanner reader;
        try {
             reader = new Scanner(inFile);
             while(reader.hasNext()){
                 convert2camel(reader.nextLine());
             }
            reader.close();
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    private void convert2camel(String line){
        // Split line til et String[] af de enkelte ord i linjen
        // Konverter 1. ord til små og resten til stort begyndelsesbogstav
        // Sammensæt ordene til ét langt ord og udskriv.

        String[] words = line.split(" ");
        String currentWord = "";
        StringBuilder camelWriter = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
             currentWord = words[i];
            char[] letters = currentWord.toCharArray();
            for (int j = 0; j < letters.length; j++) {
                if(j == 0){
                letters[j] = currentWord.replace(letters[j], currentWord.toUpperCase().charAt(j)).charAt(j);
                }
                if(i == 0){
                letters[j] = currentWord.replace(letters[j], currentWord.toLowerCase().charAt(j)).charAt(j);
                }
                
            }
            currentWord = new String(letters);
            words[i] = currentWord;
            camelWriter.append(words[i]);

        }
        writeInFile(camelWriter);
    }
    private void writeInFile(StringBuilder text){
        String newText = new String(text);
        inFile = new File("writer.txt");
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(inFile,true);
            fileWriter.write(newText + "\n");
            // den bruges, hvis man gerne vil tilføje noget filen uden og slette det hele igen.
            //fileWriter.flush();
            fileWriter.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CamelWriter cw = new CamelWriter("MaryAnn");
        cw.readLines();


    }
}
