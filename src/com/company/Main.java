package com.company;

import com.sun.javafx.fxml.expression.Expression;

import java.io.File;
import java.lang.reflect.*;
import java.util.*;
//THis class is complete. Do not change it.
class Main {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    String[] arguments;
    while(true){
      System.out.print("PROMPT\\>");      
      String command = keyboard.nextLine();  
      //Tokenize the command
      StringTokenizer tok = new StringTokenizer(command);
      
      //Save first part of Tokenized string as command
      command = tok.nextToken();
      
      //Count the number of arguments for the command
      int argumentCount = tok.countTokens();
        
      //Store the arguments in an array
      arguments = new String[argumentCount];
      for(int i = 0; i < argumentCount; i++)
        arguments[i] = tok.nextToken();
      Token token = new Token(command);
      switch (token.kind) {
        case Token.BIGGER:
          if(argumentCount == 2)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.CAL:
          if(argumentCount == 0)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("This command does not take parameters.");
          break;
        case Token.CAT:
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break; 
        case Token.ATTRIB:
          if(argumentCount > 0 && argumentCount <=2)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.CP:
          if(argumentCount == 2)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.CUT:            
          if(argumentCount == 3)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.ECHO: 
          if(argumentCount > -1){
            invokeClass(command, argumentCount, arguments);
            System.out.print("\n");
          }
          break;
        case Token.EXIT: System.exit(0); break;
        case Token.FILE: 
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
        case Token.FIND: 
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.GREP: 
          if(argumentCount == arguments.length && argumentCount >=1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.HEAD: 
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.print("You have entered the wrong number of arguments.");
          break;
        case Token.LINK: 
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
        case Token.TEST: 
    	  if(argumentCount == 2)
    		invokeClass(command, argumentCount, arguments);
          else
           	System.out.println("You have entered the wrong number of arguments.");
    		break;
        case Token.DIR: 
          if(argumentCount == 0)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("This command does not take parameters.");
          break;
        case Token.MAN: 
          if(argumentCount == 1)
            invokeClass(command, argumentCount, arguments);
          else
            System.out.println("You have entered the wrong number of arguments.");
          break;
          case Token.MKDIR: 
            if(argumentCount == 1)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
          case Token.MV: 
            if(argumentCount == 2)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
          case Token.PWD: 
            if(argumentCount == 0)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("This command does not take parameters.");
            break;
          case Token.RM: 
            if(argumentCount == 1)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
          case Token.RMDIR:
            if(argumentCount == 1)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
          case Token.TAIL: 
            if(argumentCount == 1)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
        case Token.WC: 
            if(argumentCount == 1 || argumentCount == 2)
              invokeClass(command, argumentCount, arguments);
            else
              System.out.println("You have entered the wrong number of arguments.");
            break;
          default: System.out.println("Wrong command.");
      }
    }
  }

  private static void invokeClass(String command, int argumentCount, String[] arguments){
    try {
      if(arguments.length != argumentCount){
        System.out.println("Wrong number of arguments");
        System.exit(0);
      }
      String workingDir = System.getProperty("user.dir");
      System.out.println("Here");
      System.out.println(workingDir + "/src/com/company/");
      workingDir = workingDir + "/src/com/company/";
      System.out.println("Here");
      File f = new File(workingDir + File.separator + "bin" + File.separator + command + ".class");
      if(!f.exists())
        System.out.println("File: " + command + " does not exist.");
      Class<?> c = Class.forName(command);
      Class<?>[] parType = new Class[]{String[].class};
      Method main = c.getDeclaredMethod("main", parType);
      String[] mainArgs = Arrays.copyOfRange(arguments, 0, arguments.length);
      main.invoke(null, (Object)mainArgs);
  
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }
}