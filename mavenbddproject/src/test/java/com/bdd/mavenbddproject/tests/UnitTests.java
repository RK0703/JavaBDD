package com.bdd.mavenbddproject.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UnitTests 
{
  @Test
  public void readJson() throws FileNotFoundException
  {
	  
	  String filePath = "./resources/FxParameters.json";
	 
	  FileReader reader = new FileReader(new File (filePath));
	  JsonParser par = new JsonParser();
	  JsonObject jo = new JsonObject();
	  
	  jo = (JsonObject) par.parse(reader);
	  
	  System.out.println("jo"+jo.get("name"));
  }
  
  @Test
  public void readtxt() throws IOException
  {
	  
	  Path filePath = Paths.get("./resources/text.txt");
	  Object Obj = Files.readAllLines(filePath);
	  
	 
	  System.out.println("Text in file is "+Obj);
		  
  }
  
  @Test
  public void inst()
  {
	  
	  List<Object> lis = new ArrayList();
	  
	  lis.add("a");
	  lis.add(12);
	  
	  lis.add('b');
	  
	  for(int i = 0;i<lis.size();i++)
	  {
		  
	  }
	  
	  
  }
  
  
}
